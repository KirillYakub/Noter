package com.kiras.noter.presentation.notes_overview

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiras.noter.domain.notes.model.CalendarDay
import com.kiras.noter.domain.notes.repository.NotesRepository
import com.kiras.noter.domain.use_case.FolderUseCases
import com.kiras.noter.domain.use_case.GetNotesSettingsUseCase
import com.kiras.noter.domain.use_case.NoteEditUseCase
import com.kiras.noter.domain.util.date.lastMonthToToday
import com.kiras.noter.domain.util.date.toEpochDayRange
import com.kiras.noter.mapper.toNoteUi
import com.kiras.noter.presentation.notes_overview.mapper.toCalendarDayUi
import com.kiras.noter.presentation.notes_overview.model.CalendarDayUi
import com.kiras.noter.domain.notes.model.folder.FolderDialogMode
import com.kiras.noter.domain.util.Constants.FOLDER_ALL_NAME
import com.kiras.noter.domain.util.Constants.FOLDER_IMPORTANT_NAME
import com.kiras.noter.presentation.util.Quad
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.time.Duration.Companion.milliseconds

fun TextFieldState.textAsFlow() = snapshotFlow { text }

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class NotesOverviewViewModel(
    private val notesRepository: NotesRepository,
    private val noteEditUseCase: NoteEditUseCase,
    private val getNotesSettingsUseCase: GetNotesSettingsUseCase,
    private val folderUseCases: FolderUseCases,
    private val applicationScope: CoroutineScope
) : ViewModel() {

    var state by mutableStateOf(NotesOverviewState())
        private set

    private var domainDays: List<CalendarDay>? = null

    init {
        domainDays = lastMonthToToday()
        state = state.copy(
            isLoading = true,
            calendarDays = rebuildCalendarUi(null)
        )

        folderUseCases.getFolders()
            .onEach { folders ->
                state = state.copy(folders = folders)
            }
            .launchIn(viewModelScope)

        val selectedCalendarDayFlow = snapshotFlow { state.selectedDayId }
            .distinctUntilChanged()

        val selectedFolderIdFlow = snapshotFlow { state.selectedFolder }
            .distinctUntilChanged()

        val searchQueryFlow = state.searchQuery.textAsFlow()
            .map { it.toString().trim() }
            .debounce(300.milliseconds)
            .distinctUntilChanged()

        val notesSettingsFlow = getNotesSettingsUseCase.getNotesSettingsAsFlow()
            .distinctUntilChanged()
            .shareIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                replay = 1
            )

        combine(
            selectedCalendarDayFlow,
            selectedFolderIdFlow,
            searchQueryFlow,
            notesSettingsFlow
        ) { selectedDay, selectedFolder, query, settings ->
            Quad(selectedDay, selectedFolder, query, settings)
        }
            .flatMapLatest { (selectedDay, selectedFolder, query, settings) ->
                val notesFlow = if (selectedDay != null && settings.isDateSearchEnabled) {
                    val (start, end) = LocalDate.parse(selectedDay).toEpochDayRange()
                    when (selectedFolder) {
                        FOLDER_ALL_NAME -> notesRepository.getNotesByDay(start, end, query, settings.sortType)
                        FOLDER_IMPORTANT_NAME -> notesRepository.getImportantNotesByDay(start, end, query, settings.sortType)
                        else -> notesRepository.getNotesByDayAndFolder(selectedFolder, start, end, query, settings.sortType)
                    }
                } else {
                    when (selectedFolder) {
                        FOLDER_ALL_NAME -> notesRepository.getNotes(query, settings.sortType)
                        FOLDER_IMPORTANT_NAME  -> notesRepository.getImportantNotes(query, settings.sortType)
                        else -> notesRepository.getNotesByFolder(selectedFolder, query, settings.sortType)
                    }
                }
                notesFlow.map { notes -> notes to settings }
            }
            .map { pair ->
                val notes = pair.first
                val settings = pair.second
                notes.map { it.toNoteUi() } to settings
            }
            .flowOn(Dispatchers.IO)
            .onEach { (uiNotes, settings) ->
                state = state.copy(
                    notesSortType = settings.sortType,
                    notesStyle = settings.notesStyle,
                    notesDisplayType = settings.displayType,
                    isCalendarDaysVisible = settings.isDateSearchEnabled,
                    notes = uiNotes,
                    isLoading = false
                )
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action: NotesOverviewActions) {
        when (action) {
            is NotesOverviewActions.OnDeleteNote -> {
                viewModelScope.launch {
                    notesRepository.deleteNote(action.noteId)
                }
            }
            is NotesOverviewActions.OnCopyNote -> {
                viewModelScope.launch {
                    noteEditUseCase.upsertNoteCopy(action.noteId)
                }
            }
            is NotesOverviewActions.OnFullCalendarClick -> {
                state = state.copy(
                    isFullCalendarVisible = true
                )
            }
            is NotesOverviewActions.OnDismissFullCalendar -> {
                state = state.copy(
                    isFullCalendarVisible = false
                )
            }
            is NotesOverviewActions.OnFolderSelected -> {
                state = state.copy(selectedFolder = action.folderId)
            }
            is NotesOverviewActions.OnCreateFolderClick -> {
                state = state.copy(
                    isFolderDialogOpen = true,
                    folderDialogMode = FolderDialogMode.CREATE,
                    folderToEdit = null
                )
            }
            is NotesOverviewActions.OnEditFolderClick -> {
                state = state.copy(
                    isFolderDialogOpen = true,
                    folderDialogMode = FolderDialogMode.RENAME,
                    folderToEdit = action.folder
                )
            }
            is NotesOverviewActions.OnDismissFolderDialog -> {
                state = state.copy(
                    isFolderDialogOpen = false,
                    folderDialogMode = FolderDialogMode.CREATE,
                    folderToEdit = null
                )
            }
            is NotesOverviewActions.OnCalendarDaySelected -> {
                selectCalendarDay(action.dayId)
            }
            is NotesOverviewActions.OnDeleteFolderClick -> {
                deleteFolder(action.folderId)
            }
            is NotesOverviewActions.OnSaveFolder -> {
                saveFolder(action.name)
            }
            NotesOverviewActions.OnShowDeleteFolderConfirmation -> {
                state = state.copy(
                    folderDialogMode = FolderDialogMode.DELETE
                )
            }
            NotesOverviewActions.OnCancelDeleteConfirmation -> {
                state = state.copy(
                    folderDialogMode = FolderDialogMode.RENAME
                )
            }
            else -> Unit
        }
    }

    private fun deleteFolder(folderId: String) {
        applicationScope.launch {
            folderUseCases.deleteFolder(folderId)
            if (state.selectedFolder == folderId) {
                state = state.copy(selectedFolder = FOLDER_ALL_NAME)
            }
            state = state.copy(isFolderDialogOpen = false)
        }
    }

    private fun saveFolder(folderName: String) {
        applicationScope.launch {
            when (state.folderDialogMode) {
                FolderDialogMode.CREATE -> {
                    folderUseCases.createFolder(folderName)
                }
                FolderDialogMode.RENAME -> {
                    state.folderToEdit?.let { folder ->
                        folderUseCases.renameFolder(folder.id, folderName)
                    }
                }
                else -> Unit
            }
            state = state.copy(
                isFolderDialogOpen = false,
                folderDialogMode = FolderDialogMode.CREATE,
                folderToEdit = null
            )
        }
    }

    private fun selectCalendarDay(dayId: String?) {
        val newSelectedId = if (dayId == state.selectedDayId) null else dayId
        state = state.copy(
            selectedDayId = newSelectedId,
            calendarDays = rebuildCalendarUi(newSelectedId),
            isFullCalendarVisible = false
        )
    }

    private fun rebuildCalendarUi(selectedId: String?): List<CalendarDayUi> {
        return domainDays?.map { day ->
            day.toCalendarDayUi(isSelected = (day.date.toLocalDate().toString() == selectedId))
        } ?: emptyList()
    }
}
