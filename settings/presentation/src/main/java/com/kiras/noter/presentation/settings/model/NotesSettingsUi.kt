package com.kiras.noter.presentation.settings.model

import com.kiras.noter.domain.accounts.model.AccountIcon
import com.kiras.noter.domain.notes.model.settings.NotesDisplayType
import com.kiras.noter.domain.notes.model.settings.NotesSortType
import com.kiras.noter.domain.notes.model.settings.NotesStyleType

data class NotesSettingsUi(
    val ownerAccountId: String = "",
    val accountIcon: AccountIcon = AccountIcon.ICON_1,
    val isDateSearchEnabled: Boolean = true,
    val notesStyle: NotesStyleType = NotesStyleType.COLOR_FULL,
    val displayType: NotesDisplayType = NotesDisplayType.GRID,
    val sortType: NotesSortType = NotesSortType.DATE,
)