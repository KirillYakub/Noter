package com.kiras.noter.notes.presentation.notes_overview

sealed interface NotesOverviewActions {
    data object OnNotesDisplayChange: NotesOverviewActions
    data class OnSearchQueryChange(val query: String): NotesOverviewActions
}