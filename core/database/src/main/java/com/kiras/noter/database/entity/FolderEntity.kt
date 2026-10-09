package com.kiras.noter.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import org.bson.types.ObjectId

@Entity(
    tableName = "folders",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["ownerAccountId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("ownerAccountId")
    ]
)
data class FolderEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = ObjectId().toHexString(),
    val ownerAccountId: String,
    val name: String,
    val createTime: Long = System.currentTimeMillis()
)
