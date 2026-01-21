package com.kiras.noter.notes.presentation.note_page

import com.kiras.noter.domain.model.NoteColor

sealed interface NotePageActions {
    data class OnTitleChange(val title: String) : NotePageActions
    data class OnContentChange(val content: String) : NotePageActions
    data class OnColorChange(val color: NoteColor) : NotePageActions
    data object OnBackClick : NotePageActions
//    data object OnLikeClick : NotePageActions
//    data object OnFolderClick : NotePageActions
//    data object OnSendClick : NotePageActions
}