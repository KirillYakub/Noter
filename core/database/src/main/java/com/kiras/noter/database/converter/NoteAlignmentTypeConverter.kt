package com.kiras.noter.database.converter

import androidx.room.TypeConverter
import com.kiras.noter.domain.notes.model.NoteAlignment

class NoteAlignmentTypeConverter {

    @TypeConverter
    fun fromNoteAlignment(alignment: NoteAlignment): String {
        return alignment.name
    }

    @TypeConverter
    fun toNoteAlignment(noteAlignmentName: String): NoteAlignment {
        return NoteAlignment.valueOf(noteAlignmentName)
    }
}