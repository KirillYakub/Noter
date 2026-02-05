package com.kiras.noter.presentation.settings.model

import com.kiras.noter.domain.notes.model.settings.NotesDisplayType
import com.kiras.noter.domain.notes.model.settings.NotesSortType

data class NotesSettingsUi(
    val ownerAccountId: String = "",
    val isDateSearchEnabled: Boolean = true,
    val displayType: NotesDisplayType = NotesDisplayType.GRID,
    val sortType: NotesSortType = NotesSortType.DATE,
)