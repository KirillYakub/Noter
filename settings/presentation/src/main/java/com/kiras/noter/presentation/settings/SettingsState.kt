package com.kiras.noter.presentation.settings

import com.kiras.noter.presentation.settings.model.NotesSettingsUi

data class SettingsState(
    val accountNotesSettings: NotesSettingsUi = NotesSettingsUi(),
)
