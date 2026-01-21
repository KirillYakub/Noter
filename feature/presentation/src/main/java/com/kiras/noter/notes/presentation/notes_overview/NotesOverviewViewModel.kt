package com.kiras.noter.notes.presentation.notes_overview

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiras.noter.domain.model.CalendarDay
import com.kiras.noter.domain.repository.NotesRepository
import com.kiras.noter.domain.util.date.lastMonthToToday
import com.kiras.noter.domain.util.date.toEpochDayRange
import com.kiras.noter.notes.presentation.notes_overview.mapper.toCalendarDayUi
import com.kiras.noter.notes.mapper.toNoteUi
import com.kiras.noter.notes.presentation.notes_overview.model.CalendarDayUi
import com.kiras.noter.notes.presentation.notes_overview.model.NoteDisplay
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate

fun TextFieldState.textAsFlow() = snapshotFlow { text }

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class NotesOverviewViewModel(
    private val notesRepository: NotesRepository,
    private val applicationScope: CoroutineScope
) : ViewModel() {

    var state by mutableStateOf(NotesOverviewState())
        private set

    private var domainDays: List<CalendarDay>? = null

    init {
        domainDays = lastMonthToToday()
        state = state.copy(
            calendarDays = rebuildCalendarUi(null)
        )

        val selectedCalendarDayFlow = snapshotFlow { state.selectedDayId }
            .distinctUntilChanged()

        val searchQueryFlow = state.searchQuery.textAsFlow()
            .map { it.toString().trim() }
            .debounce(300)
            .distinctUntilChanged()

        combine(selectedCalendarDayFlow, searchQueryFlow) { selectedDay, query ->
            selectedDay to query
        }
            .flatMapLatest { (selectedDay, query) ->
                val base =
                    if (selectedDay == null) {
                        notesRepository.getNotes()
                    } else {
                        val (start, end) = LocalDate.parse(selectedDay).toEpochDayRange()
                        notesRepository.getNotesByDay(start, end)
                    }

                if (query.isBlank()) base
                else {
                    base.map { list ->
                        list.filter { note ->
                            note.title.contains(query, ignoreCase = true) ||
                                    note.content.contains(query, ignoreCase = true)
                        }
                    }
                }
            }
            .filterNotNull()
            .map { notes -> notes.map { it.toNoteUi() } }
            .onEach { uiNotes -> state = state.copy(notes = uiNotes) }
            .launchIn(viewModelScope)
    }

    fun onAction(action: NotesOverviewActions) {
        when (action) {
            NotesOverviewActions.OnNotesDisplayChange -> changeNotesDisplayStyle()
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

    private fun changeNotesDisplayStyle() {
        val displayStyle = if(state.noteDisplay == NoteDisplay.LIST)
            NoteDisplay.GRID
        else
            NoteDisplay.LIST
        state = state.copy(noteDisplay = displayStyle)
    }
}