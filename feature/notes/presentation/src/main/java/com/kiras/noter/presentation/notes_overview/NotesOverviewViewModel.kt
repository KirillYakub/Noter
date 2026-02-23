package com.kiras.noter.presentation.notes_overview

import android.util.Log
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiras.noter.domain.notes.model.CalendarDay
import com.kiras.noter.domain.notes.repository.NotesRepository
import com.kiras.noter.domain.util.date.lastMonthToToday
import com.kiras.noter.domain.util.date.toEpochDayRange
import com.kiras.noter.domain.notes.model.settings.toComparator
import com.kiras.noter.domain.use_case.GetNotesSettingsUseCase
import com.kiras.noter.domain.use_case.NoteEditUseCase
import com.kiras.noter.mapper.toNoteUi
import com.kiras.noter.presentation.notes_overview.mapper.toCalendarDayUi
import com.kiras.noter.presentation.notes_overview.model.CalendarDayUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import java.time.LocalDate

fun TextFieldState.textAsFlow() = snapshotFlow { text }

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class NotesOverviewViewModel(
    private val notesRepository: NotesRepository,
    private val noteEditUseCase: NoteEditUseCase,
    private val getNotesSettingsUseCase: GetNotesSettingsUseCase,
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

        val selectedCalendarDayFlow = snapshotFlow { state.selectedDayId }
            .distinctUntilChanged()

        val searchQueryFlow = state.searchQuery.textAsFlow()
            .map { it.toString().trim() }
            .debounce(300)
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
            searchQueryFlow,
            notesSettingsFlow
        ) { selectedDay, query, settings ->
            Triple(selectedDay, query, settings)
        }
            .flatMapLatest { (selectedDay, query, settings) ->
                val notesFlow = if (selectedDay != null && settings.isDateSearchEnabled) {
                    val (start, end) = LocalDate.parse(selectedDay).toEpochDayRange()
                    notesRepository.getNotesByDay(
                        dayStart = start,
                        dayEnd = end,
                        query = query,
                        sortType = settings.sortType
                    )
                }
                else {
                    notesRepository.getNotes(
                        query = query,
                        sortType = settings.sortType
                    )
                }
                notesFlow.map { notes -> notes to settings }
            }
            .map { (notes, settings) -> notes.map { it.toNoteUi() } to settings }
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
            is NotesOverviewActions.OnCalendarDaySelected -> {
                val newSelectedId =
                    if (action.dayId == state.selectedDayId) null
                    else action.dayId
                state = state.copy(
                    selectedDayId = newSelectedId,
                    calendarDays = rebuildCalendarUi(newSelectedId)
                )
            }
            else -> Unit
        }
    }

    private fun rebuildCalendarUi(selectedId: String?): List<CalendarDayUi> {
        return domainDays?.map { day ->
            day.toCalendarDayUi(isSelected = (day.date.toLocalDate().toString() == selectedId))
        } ?: emptyList()
    }
}