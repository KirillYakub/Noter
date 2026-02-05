package com.kiras.noter.notes.presentation.notes_overview

import androidx.compose.foundation.text.input.TextFieldState
import com.kiras.noter.notes.presentation.notes_overview.model.CalendarDayUi
import com.kiras.noter.domain.notes.model.settings.NotesDisplayType
import com.kiras.noter.notes.model.NoteUi

data class NotesOverviewState(
    val searchQuery: TextFieldState = TextFieldState(),
    val notesDisplayType: NotesDisplayType = NotesDisplayType.GRID,
    val calendarDays: List<CalendarDayUi> = emptyList(),
    val selectedDayId: String? = null,
    val notes: List<NoteUi> = emptyList()
)