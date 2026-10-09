package com.kiras.noter.domain.notes.model.folder

import java.time.ZonedDateTime

data class Folder(
    val id: String,
    val ownerAccountId: String,
    val name: String,
    val createTime: ZonedDateTime
)
