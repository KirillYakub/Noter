package com.kiras.noter.database

import com.kiras.noter.database.dao.AccountNotesSettingsDao
import com.kiras.noter.database.mappers.toNotesSettings
import com.kiras.noter.database.mappers.toNotesSettingsEntity
import com.kiras.noter.domain.accounts.repository.settings.AccountNotesSettingsLocalDataSource
import com.kiras.noter.domain.accounts.repository.settings.NotesSettingsId
import com.kiras.noter.domain.notes.model.settings.NotesSettings
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomAccountNotesSettingsLocalDataSourceImpl(
    private val accountNotesSettingsDao: AccountNotesSettingsDao
) : AccountNotesSettingsLocalDataSource {

    override fun getAccountNotesSettingsAsFlow(
        ownerAccountId: String
    ): Flow<NotesSettings> {
        return accountNotesSettingsDao
            .getAccountNotesSettingsByIdAsFlow(ownerAccountId)
            .map { it.toNotesSettings() }
    }

    override suspend fun getAccountNotesSettings(ownerAccountId: String): NotesSettings {
        return accountNotesSettingsDao
            .getAccountNotesSettingsById(ownerAccountId)
            .toNotesSettings()
    }

    override suspend fun setAccountNotesSettings(notesSettings: NotesSettings): Result<NotesSettingsId, DataError.Local> {
        accountNotesSettingsDao.updateAccountNotesSettings(
            notesSettings.toNotesSettingsEntity()
        )
        return Result.Success(notesSettings.ownerAccountId)
    }
}