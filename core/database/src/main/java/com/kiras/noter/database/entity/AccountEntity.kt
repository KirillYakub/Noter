package com.kiras.noter.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kiras.noter.domain.accounts.model.AccountIcon
import org.bson.types.ObjectId

@Entity(
    tableName = "accounts",
    indices = [
        Index("email", unique = true)
    ]
)
data class AccountEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = ObjectId().toHexString(),
    val email: String,
    val passwordHash: String,
    val name: String,
    val icon: AccountIcon,
    val lastSignIn: Long
)
