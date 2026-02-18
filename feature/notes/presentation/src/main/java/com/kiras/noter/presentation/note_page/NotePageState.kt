package com.kiras.noter.presentation.note_page

import com.kiras.noter.domain.notes.model.settings.NotesStyleType
import com.kiras.noter.model.NoteUi
import com.kiras.noter.presentation.note_page.model.NoteAlignment

data class NotePageState (
    val showNoteContent: Boolean = false,
    val alignment: NoteAlignment = NoteAlignment.START,
    val noteStyleType: NotesStyleType = NotesStyleType.COLOR_FULL,
    val noteUi: NoteUi = NoteUi()
) {
    fun isNoteBlank(): Boolean {
        return noteUi.title.isBlank() && noteUi.content.isBlank()
    }
}