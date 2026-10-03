package com.kiras.noter.presentation.note_page

import com.kiras.noter.domain.notes.model.NoteColor
import com.kiras.noter.domain.notes.model.NoteAlignment

sealed interface NotePageActions {
    data class OnTitleChange(val title: String) : NotePageActions
    data class OnContentChange(val content: String) : NotePageActions
    data class OnColorChange(val color: NoteColor) : NotePageActions
    data class OnAlignChange(val align: NoteAlignment) : NotePageActions
    data object OnBackClick : NotePageActions
    data object OnSendClick : NotePageActions
}