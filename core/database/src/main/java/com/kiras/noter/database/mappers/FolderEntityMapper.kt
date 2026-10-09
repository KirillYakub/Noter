package com.kiras.noter.database.mappers

import com.kiras.noter.database.entity.FolderEntity
import com.kiras.noter.domain.notes.model.folder.Folder
import java.time.Instant
import java.time.ZoneId

fun FolderEntity.toFolder(): Folder {
    return Folder(
        id = id,
        ownerAccountId = ownerAccountId,
        name = name,
        createTime = Instant.ofEpochMilli(createTime).atZone(ZoneId.systemDefault())
    )
}

fun Folder.toFolderEntity(): FolderEntity {
    return FolderEntity(
        id = id,
        ownerAccountId = ownerAccountId,
        name = name,
        createTime = createTime.toInstant().toEpochMilli()
    )
}
