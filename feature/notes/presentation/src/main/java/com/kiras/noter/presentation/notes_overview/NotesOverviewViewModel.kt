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
import com.kiras.noter.domain.util.date.lastMonthToToday
import com.kiras.noter.domain.util.date.toEpochDayRange
import com.kiras.noter.domain.notes.model.settings.toComparator
import com.kiras.noter.domain.use_case.GetNotesSettingsUseCase
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
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import java.time.LocalDate

fun TextFieldState.textAsFlow() = snapshotFlow { text }

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class NotesOverviewViewModel(
    private val notesRepository: NotesRepository,
    private val getNotesSettingsUseCase: GetNotesSettingsUseCase,
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

        val notesSettingsFlow = getNotesSettingsUseCase.getNotesSettingsAsFlow()
            .distinctUntilChanged()
            .shareIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                replay = 1
            )

        notesSettingsFlow.onEach { settings ->
            state = state.copy(
                notesSortType = settings.sortType,
                notesStyle = settings.notesStyle,
                notesDisplayType = settings.displayType,
                isCalendarDaysVisible = settings.isDateSearchEnabled
            )
        }.launchIn(viewModelScope)

        combine(
            selectedCalendarDayFlow,
            searchQueryFlow,
            notesSettingsFlow
        ) { selectedDay, query, notesSettings ->
            Triple(selectedDay, query, notesSettings)
        }
            .flowOn(Dispatchers.IO)
            .flatMapLatest { (selectedDay, query, notesSettings) ->

                val base = when {
                    selectedDay == null || !notesSettings.isDateSearchEnabled -> {
                        notesRepository.getNotes()
                    }
                    else -> {
                        val (start, end) = LocalDate.parse(selectedDay).toEpochDayRange()
                        notesRepository.getNotesByDay(start, end)
                    }
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
            .map { notes -> notes.sortedWith(state.notesSortType.toComparator()) }
            .map { notes -> notes.map { it.toNoteUi() } }
            .onEach { uiNotes -> state = state.copy(notes = uiNotes) }
            .launchIn(viewModelScope)
    }

    fun onAction(action: NotesOverviewActions) {
        when (action) {
            is NotesOverviewActions.OnDeleteNote -> {
                viewModelScope.launch {
                    notesRepository.deleteNote(action.noteId)
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