package com.kiras.noter.notes.presentation.notes_overview.model

import com.kiras.noter.domain.model.NoteColor

data class NoteUi(
    val id: String,
    val title: String,
    val content: String,
    val color: NoteColor,
    val createTime: String,
    val updateTime: String
)
