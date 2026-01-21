package com.kiras.noter.domain.model

import java.time.ZonedDateTime

data class Note(
    val id: String? = null,
    val title: String,
    val content: String,
    val color: NoteColor,
    val createTime: ZonedDateTime
)
