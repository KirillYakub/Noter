package com.kiras.noter.database.mappers

import com.kiras.noter.database.entity.AccountNotesSettingsEntity
import com.kiras.noter.domain.notes.model.settings.NotesSettings

fun AccountNotesSettingsEntity.toNotesSettings(): NotesSettings {
    return NotesSettings(
        ownerAccountId = ownerAccountId,
        notesStyle = notesStyleType,
        isDateSearchEnabled = isDateSearchEnabled,
        displayType = displayType,
        sortType = sortType
    )
}

fun NotesSettings.toNotesSettingsEntity(): AccountNotesSettingsEntity {
    return AccountNotesSettingsEntity(
        ownerAccountId = ownerAccountId,
        notesStyleType = notesStyle,
        isDateSearchEnabled = isDateSearchEnabled,
        displayType = displayType,
        sortType = sortType
    )
}