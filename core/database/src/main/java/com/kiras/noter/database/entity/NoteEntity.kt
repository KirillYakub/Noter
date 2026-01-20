package com.kiras.noter.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kiras.noter.domain.model.NoteColor
import org.bson.types.ObjectId

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = ObjectId().toHexString(),
    val title: String,
    val content: String,
    val color: NoteColor,
    val createTime: Long,
    val updateTime: Long
)