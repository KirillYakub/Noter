package com.kiras.noter.database.mappers

import com.kiras.noter.database.entity.NoteEntity
import com.kiras.noter.domain.model.Note
import org.bson.types.ObjectId
import java.time.Instant
import java.time.ZoneId

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        color = color,
        createTime = Instant.ofEpochMilli(createTime).atZone(ZoneId.systemDefault()),
        updateTime = Instant.ofEpochMilli(updateTime).atZone(ZoneId.systemDefault())
    )
}

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = id ?: ObjectId().toHexString(),
        title = title,
        content = content,
        color = color,
        createTime = createTime.toInstant().toEpochMilli(),
        updateTime = updateTime.toInstant().toEpochMilli()
    )
}