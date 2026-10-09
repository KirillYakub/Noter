package com.kiras.noter.presentation.note_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.kiras.noter.domain.use_case.FolderUseCases
import com.kiras.noter.domain.use_case.GetNotesSettingsUseCase
import com.kiras.noter.domain.use_case.NoteEditUseCase
import com.kiras.noter.mapper.toNote
import com.kiras.noter.mapper.toNoteUi
import com.kiras.noter.presentation.note_page.mapper.toFolderSelectionUi
import com.kiras.noter.presentation.note_page.model.NoteDraft
import com.kiras.noter.presentation.util.NotePage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@OptIn(FlowPreview::class)
class NotePageViewModel(
    private val noteEditUseCase: NoteEditUseCase,
    private val getNotesSettingsUseCase: GetNotesSettingsUseCase,
    folderUseCases: FolderUseCases,
    applicationScope: CoroutineScope,
    saveStateHandle: SavedStateHandle
) : ViewModel() {

    var notePageState by mutableStateOf(NotePageState())
        private set

    private val currentNoteIdState = MutableStateFlow("")
    private var noteDraft = NoteDraft()

    init {
        val args = saveStateHandle.toRoute<NotePage>()

        viewModelScope.launch {
            val noteResult = async {
                args.id?.let { id -> noteEditUseCase.getNote(id) }
                    ?: noteEditUseCase.createEmptyNote()
            }
            val noteStyleTypeResult = async {
                getNotesSettingsUseCase.getNotesSettings().notesStyle
            }
            val note = noteResult.await()
            val noteStyleType = noteStyleTypeResult.await()
            currentNoteIdState.value = note.id
            noteDraft = noteDraft.copy(
                noteCreateTime = note.createTime,
                ownerAccountId = note.ownerAccountId
            )
            if (args.id != null) {
                noteDraft.selectedFolderIds.addAll(note.folderIds)
            }
            notePageState = notePageState.copy(
                showNoteContent = true,
                noteStyleType = noteStyleType,
                noteUi = note.toNoteUi(),
                isImportant = note.isImportant
            )
        }

        combine(
            folderUseCases.getFolders(),
            folderUseCases.getFolderIdsForNoteAsFlow(currentNoteIdState.value)
        ) { folders, assignedFolderIds ->
            val effectiveFolderIds = (assignedFolderIds + noteDraft.selectedFolderIds).distinct()
            folders.map { folder ->
                folder.toFolderSelectionUi(isSelected = effectiveFolderIds.contains(folder.id))
            }
        }.onEach { folderSelections ->
            notePageState = notePageState.copy(folders = folderSelections)
        }.launchIn(viewModelScope)

        snapshotFlow { notePageState }
            .debounce(300.milliseconds)
            .distinctUntilChanged()
            .onEach { notePageStateLatest ->
                when {
                    noteDraft.noteCreateTime == null -> return@onEach
                    notePageStateLatest.isNoteBlank() -> {
                        noteEditUseCase.deleteNote(id = notePageStateLatest.noteUi.id)
                    }
                    else -> {
                        val selectedFolderIds = notePageStateLatest.folders
                            .filter { it.isSelected }
                            .map { it.id } + noteDraft.selectedFolderIds
                        val uniqueFolderIds = selectedFolderIds.distinct()
                        noteEditUseCase.upsertNote(
                            note = notePageStateLatest.noteUi.toNote(
                                ownerAccountId = noteDraft.ownerAccountId!!,
                                createTimeAsZoneDateTime = noteDraft.noteCreateTime!!,
                                isImportant = notePageStateLatest.isImportant,
                                folderIds = uniqueFolderIds
                            )
                        )
                    }
                }
            }
            .launchIn(applicationScope)
    }

    fun onAction(action: NotePageActions) {
        when(action) {
            is NotePageActions.OnColorChange -> {
                notePageState = notePageState.copy(
                    noteUi = notePageState.noteUi.copy(color = action.color)
                )
            }
            is NotePageActions.OnContentChange -> {
                notePageState = notePageState.copy(
                    noteUi = notePageState.noteUi.copy(content = action.content)
                )
            }
            is NotePageActions.OnTitleChange -> {
                notePageState = notePageState.copy(
                    noteUi = notePageState.noteUi.copy(title = action.title)
                )
            }
            is NotePageActions.OnAlignChange -> {
                notePageState = notePageState.copy(
                    noteUi = notePageState.noteUi.copy(alignment = action.align)
                )
            }
            is NotePageActions.OnToggleImportant -> {
                notePageState = notePageState.copy(isImportant = !notePageState.isImportant)
            }
            is NotePageActions.OnToggleFoldersMenu -> {
                notePageState = notePageState.copy(isFoldersMenuOpen = action.isOpen)
            }
            is NotePageActions.OnToggleFolderSelection -> {
                toggleFolderSelection(action.folderId, action.isSelected)
            }
            else -> Unit
        }
    }

    private fun toggleFolderSelection(folderId: String, isSelected: Boolean) {
        if (isSelected) noteDraft.selectedFolderIds.add(folderId)
        else noteDraft.selectedFolderIds.remove(folderId)

        val updatedFolders = notePageState.folders.map { folder ->
            if (folder.id == folderId) folder.copy(isSelected = isSelected)
            else folder
        }
        notePageState = notePageState.copy(folders = updatedFolders)
    }
}