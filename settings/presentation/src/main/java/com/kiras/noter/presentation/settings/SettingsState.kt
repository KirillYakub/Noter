package com.kiras.noter.presentation.settings

import com.kiras.noter.domain.notes.model.settings.NotesSettings

data class SettingsState(
    val accountNotesSettings: NotesSettings? = null,
    val isLoading: Boolean = true
)
