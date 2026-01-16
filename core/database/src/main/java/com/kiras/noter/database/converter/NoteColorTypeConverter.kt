package com.kiras.noter.database.converter

import androidx.room.TypeConverter
import com.kiras.noter.domain.model.NoteColor

class NoteColorTypeConverter {

    @TypeConverter
    fun fromNoteColor(noteColor: NoteColor): String {
        return noteColor.name
    }

    @TypeConverter
    fun toNoteColor(noteColorName: String): NoteColor {
        return NoteColor.valueOf(noteColorName)
    }
}