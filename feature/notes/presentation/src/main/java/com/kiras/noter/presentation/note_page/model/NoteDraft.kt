package com.kiras.noter.presentation.note_page.model

import java.time.ZonedDateTime

data class NoteDraft(
    var noteCreateTime: ZonedDateTime? = null,
    var ownerAccountId: String? = null
)
