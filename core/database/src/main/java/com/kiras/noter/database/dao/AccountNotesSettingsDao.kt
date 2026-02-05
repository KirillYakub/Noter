package com.kiras.noter.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kiras.noter.database.entity.AccountNotesSettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountNotesSettingsDao {

    @Query("SELECT * FROM account_notes_settings WHERE ownerAccountId = :ownerAccountId LIMIT 1")
    fun getAccountNotesSettingsByIdAsFlow(ownerAccountId: String): Flow<AccountNotesSettingsEntity>

    @Query("SELECT * FROM account_notes_settings WHERE ownerAccountId = :ownerAccountId LIMIT 1")
    suspend fun getAccountNotesSettingsById(ownerAccountId: String): AccountNotesSettingsEntity

    @Upsert
    suspend fun updateAccountNotesSettings(accountNotesSettingsEntity: AccountNotesSettingsEntity)
}