package com.kiras.noter.model

import com.kiras.noter.domain.notes.model.NoteColor
import com.kiras.noter.domain.notes.model.NoteAlignment

data class NoteUi(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val alignment: NoteAlignment = NoteAlignment.START,
    val color: NoteColor = NoteColor.DEFAULT,
    val createTime: String = "",
    val isImportant: Boolean = false
)