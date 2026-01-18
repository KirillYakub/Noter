package com.kiras.noter.notes.presentation.notes_overview.mapper

import com.kiras.noter.domain.model.Note
import com.kiras.noter.notes.presentation.notes_overview.model.NoteUi
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Note.toNoteUi(): NoteUi {
    val formatTimePattern = "dd.MM.yyyy HH:mm"
    val createTimeInLocalTime = createTime
        .withZoneSameInstant(ZoneId.systemDefault())
    val updateTimeInLocalTime = updateTime
        .withZoneSameInstant(ZoneId.systemDefault())

    val formattedCreateTime = DateTimeFormatter
        .ofPattern(formatTimePattern)
        .format(createTimeInLocalTime)
    val formattedUpdateTime = DateTimeFormatter
        .ofPattern(formatTimePattern)
        .format(updateTimeInLocalTime)

    return NoteUi(
        id = id!!,
        title = title,
        content = content,
        color = color,
        createTime = formattedCreateTime,
        updateTime = formattedUpdateTime
    )
}