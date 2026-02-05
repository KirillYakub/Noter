package com.kiras.noter.notes.presentation.notes_overview

sealed interface NotesOverviewActions {
    data object OnNotesDisplayChange: NotesOverviewActions
    data object OnMenuClick: NotesOverviewActions
    data object OnAddNote: NotesOverviewActions
    data class OnNoteClick(val noteId: String): NotesOverviewActions
    data class OnDeleteNote(val noteId: String): NotesOverviewActions
    data class OnCalendarDaySelected(val dayId: String?) : NotesOverviewActions
}