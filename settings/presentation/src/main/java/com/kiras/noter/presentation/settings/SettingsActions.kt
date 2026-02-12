package com.kiras.noter.presentation.settings

import com.kiras.noter.domain.notes.model.settings.NotesDisplayType
import com.kiras.noter.domain.notes.model.settings.NotesSortType
import com.kiras.noter.domain.notes.model.settings.NotesStyleType

sealed interface SettingsActions {
    data object OnBackClick: SettingsActions
    data object OnLogoutClick: SettingsActions
    data class OnNotesStyleChange(val notesStyle: NotesStyleType): SettingsActions
    data class OnNotesDisplayChange(val displayType: NotesDisplayType): SettingsActions
    data class OnNotesSortChange(val sortType: NotesSortType): SettingsActions
    data class OnDateSearchEnableChange(val enable: Boolean): SettingsActions
}