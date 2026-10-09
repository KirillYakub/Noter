package com.kiras.noter.domain.notes.model

import java.time.ZonedDateTime

data class Note(
    val id: String,
    val ownerAccountId: String,
    val title: String,
    val content: String,
    val color: NoteColor,
    val alignment: NoteAlignment,
    val createTime: ZonedDateTime,
    val isImportant: Boolean = false,
    val folderIds: List<String> = emptyList()
)
