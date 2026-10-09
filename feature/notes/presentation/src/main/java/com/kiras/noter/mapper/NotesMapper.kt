package com.kiras.noter.mapper

import com.kiras.noter.domain.notes.model.Note
import com.kiras.noter.model.NoteUi
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun Note.toNoteUi(): NoteUi {
    val formatTimePattern = "dd.MM.yyyy HH:mm"
    val createTimeInLocalTime = createTime
        .withZoneSameInstant(ZoneId.systemDefault())

    val formattedCreateTimeAsString = DateTimeFormatter
        .ofPattern(formatTimePattern)
        .format(createTimeInLocalTime)

    return NoteUi(
        id = id,
        title = title,
        content = content,
        color = color,
        alignment = alignment,
        createTime = formattedCreateTimeAsString,
        isImportant = isImportant
    )
}

fun NoteUi.toNote(
    ownerAccountId: String,
    createTimeAsZoneDateTime: ZonedDateTime,
    isImportant: Boolean = false,
    folderIds: List<String> = emptyList()
): Note {
    return Note(
        id = id,
        ownerAccountId = ownerAccountId,
        title = title,
        content = content,
        color = color,
        alignment = alignment,
        createTime = createTimeAsZoneDateTime,
        isImportant = isImportant,
        folderIds = folderIds
    )
}