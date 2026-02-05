package com.kiras.noter.database.converter

import androidx.room.TypeConverter
import com.kiras.noter.domain.notes.model.settings.NotesDisplayType
import com.kiras.noter.domain.notes.model.settings.NotesSortType

class AccountNotesSettingsTypeConverter {

    @TypeConverter
    fun toNotesDisplayType(value: String): NotesDisplayType {
        return NotesDisplayType.valueOf(value)
    }

    @TypeConverter
    fun fromNotesDisplayType(value: NotesDisplayType): String {
        return value.name
    }

    @TypeConverter
    fun toNotesSortType(value: String): NotesSortType {
        return NotesSortType.valueOf(value)
    }

    @TypeConverter
    fun fromNotesSortType(value: NotesSortType): String {
        return value.name
    }
}