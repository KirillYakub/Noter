package com.kiras.noter.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kiras.noter.domain.notes.model.settings.NotesDisplayType
import com.kiras.noter.domain.notes.model.settings.NotesSortType

@Entity(
    tableName = "account_notes_settings",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["ownerAccountId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("ownerAccountId", unique = true)]
)
data class AccountNotesSettingsEntity(
    @PrimaryKey(autoGenerate = false)
    val ownerAccountId: String,
    val isDateSearchEnabled: Boolean,
    val displayType: NotesDisplayType,
    val sortType: NotesSortType
)
