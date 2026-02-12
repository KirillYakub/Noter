package com.kiras.noter.domain.notes.model.settings

data class NotesSettings (
    val ownerAccountId: String,
    val isDateSearchEnabled: Boolean,
    val notesStyle: NotesStyleType,
    val displayType: NotesDisplayType,
    val sortType: NotesSortType
)