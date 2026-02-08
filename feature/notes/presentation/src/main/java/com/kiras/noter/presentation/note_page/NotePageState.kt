package com.kiras.noter.presentation.note_page

import com.kiras.noter.model.NoteUi
import com.kiras.noter.presentation.note_page.model.NoteAlignment

data class NotePageState (
    val alignment: NoteAlignment = NoteAlignment.START,
    val noteUi: NoteUi = NoteUi()
) {
    fun isNoteBlank(): Boolean {
        return noteUi.title.isBlank() && noteUi.content.isBlank()
    }
}