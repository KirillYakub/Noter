package com.kiras.noter.notes.model

import com.kiras.noter.domain.model.NoteColor
import java.time.ZonedDateTime

data class NoteUi(
    val id: String? = null,
    val title: String = "",
    val content: String = "",
    val color: NoteColor = NoteColor.DEFAULT,
    val createTime: String = ""
)