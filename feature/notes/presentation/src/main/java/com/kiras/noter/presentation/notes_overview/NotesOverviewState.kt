package com.kiras.noter.presentation.notes_overview

import androidx.compose.foundation.text.input.TextFieldState
import com.kiras.noter.domain.notes.model.settings.NotesDisplayType
import com.kiras.noter.domain.notes.model.settings.NotesSortType
import com.kiras.noter.model.NoteUi
import com.kiras.noter.presentation.notes_overview.model.CalendarDayUi

data class NotesOverviewState(
    val searchQuery: TextFieldState = TextFieldState(),
    val notesSortType: NotesSortType = NotesSortType.DATE,
    val notesDisplayType: NotesDisplayType = NotesDisplayType.GRID,
    val isCalendarDaysVisible: Boolean = true,
    val calendarDays: List<CalendarDayUi> = emptyList(),
    val selectedDayId: String? = null,
    val notes: List<NoteUi> = emptyList()
)