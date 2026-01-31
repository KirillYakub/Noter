package com.kiras.noter.database.mappers

import com.kiras.noter.database.entity.NoteEntity
import com.kiras.noter.domain.notes.model.Note
import org.bson.types.ObjectId
import java.time.Instant
import java.time.ZoneId

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        ownerAccountId = ownerAccountId,
        title = title,
        content = content,
        color = color,
        createTime = Instant.ofEpochMilli(createTime).atZone(ZoneId.systemDefault()),
    )
}

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        ownerAccountId = ownerAccountId,
        title = title,
        content = content,
        color = color,
        createTime = createTime.toInstant().toEpochMilli(),
    )
}