package com.kiras.noter.presentation.settings.mapper

import com.kiras.noter.domain.notes.model.settings.NotesSettings
import com.kiras.noter.presentation.settings.model.NotesSettingsUi

fun NotesSettings.toNotesSettingsUi(): NotesSettingsUi {
    return NotesSettingsUi(
        ownerAccountId = ownerAccountId,
        notesStyle = notesStyle,
        isDateSearchEnabled = isDateSearchEnabled,
        displayType = displayType,
        sortType = sortType
    )
}

fun NotesSettingsUi.toNotesSettings(): NotesSettings {
    return NotesSettings(
        ownerAccountId = ownerAccountId,
        notesStyle = notesStyle,
        isDateSearchEnabled = isDateSearchEnabled,
        displayType = displayType,
        sortType = sortType
    )
}

