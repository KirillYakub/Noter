package com.kiras.noter.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kiras.noter.domain.notes.model.NoteAlignment
import com.kiras.noter.domain.notes.model.NoteColor
import org.bson.types.ObjectId

@Entity(
    tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["ownerAccountId"],
            onDelete = ForeignKey.CASCADE
        )],
    indices = [
        Index("ownerAccountId", "createTime")
    ]
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val ownerAccountId: String,
    val title: String,
    val content: String,
    val color: NoteColor,
    val alignment: NoteAlignment,
    val createTime: Long
)