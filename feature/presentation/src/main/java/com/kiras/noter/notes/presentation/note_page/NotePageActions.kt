package com.kiras.noter.notes.presentation.note_page

import com.kiras.noter.domain.model.NoteColor
import com.kiras.noter.notes.presentation.note_page.model.NoteAlignment

sealed interface NotePageActions {
    data class OnTitleChange(val title: String) : NotePageActions
    data class OnContentChange(val content: String) : NotePageActions
    data class OnColorChange(val color: NoteColor) : NotePageActions
    data class OnAlignChange(val align: NoteAlignment) : NotePageActions
    data object OnBackClick : NotePageActions
}