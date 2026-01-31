package com.kiras.noter.notes.model

import com.kiras.noter.domain.notes.model.NoteColor

data class NoteUi(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val color: NoteColor = NoteColor.DEFAULT,
    val createTime: String = ""
)