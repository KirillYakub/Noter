package com.kiras.noter.notes.presentation.notes_overview

import com.kiras.noter.notes.presentation.notes_overview.model.NoteDisplay
import com.kiras.noter.notes.presentation.notes_overview.model.NoteUi

data class NotesOverviewState(
    val searchQuery: String = "",
    val noteDisplay: NoteDisplay = NoteDisplay.GRID,
    val notes: List<NoteUi> = emptyList()
)
