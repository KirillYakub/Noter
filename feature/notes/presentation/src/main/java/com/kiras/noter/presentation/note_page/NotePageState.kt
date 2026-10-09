package com.kiras.noter.presentation.note_page

import com.kiras.noter.domain.notes.model.settings.NotesStyleType
import com.kiras.noter.model.NoteUi
import com.kiras.noter.domain.notes.model.NoteAlignment
import com.kiras.noter.presentation.note_page.model.FolderSelectionUi

data class NotePageState (
    val showNoteContent: Boolean = false,
    val noteStyleType: NotesStyleType = NotesStyleType.COLOR_FULL,
    val noteUi: NoteUi = NoteUi(),
    val isImportant: Boolean = false,
    val folders: List<FolderSelectionUi> = emptyList(),
    val isFoldersMenuOpen: Boolean = false
) {
    fun isNoteBlank(): Boolean {
        return noteUi.title.isBlank() && noteUi.content.isBlank()
    }
}
