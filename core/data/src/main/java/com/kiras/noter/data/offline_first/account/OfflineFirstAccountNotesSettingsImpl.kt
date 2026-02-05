package com.kiras.noter.data.offline_first.account

import com.kiras.noter.domain.accounts.repository.AuthActiveSessionStorage
import com.kiras.noter.domain.accounts.repository.settings.AccountNotesSettingsLocalDataSource
import com.kiras.noter.domain.accounts.repository.settings.AccountNotesSettingsRepository
import com.kiras.noter.domain.notes.model.settings.NotesSettings
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.EmptyResult
import com.kiras.noter.domain.util.Result
import com.kiras.noter.domain.util.asEmptyDataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class OfflineFirstAccountNotesSettingsImpl(
    private val localDataSource: AccountNotesSettingsLocalDataSource,
    private val authActiveSessionStorage: AuthActiveSessionStorage,
    private val applicationScope: CoroutineScope
): AccountNotesSettingsRepository {

    override suspend fun getAccountNotesSettingsAsFlow(): Flow<NotesSettings> {
        return localDataSource.getAccountNotesSettingsAsFlow(
            ownerAccountId = authActiveSessionStorage.get()!!.userId
        )
    }

    override suspend fun getAccountNotesSettings(): NotesSettings {
        return localDataSource.getAccountNotesSettings(
            ownerAccountId = authActiveSessionStorage.get()!!.userId
        )
    }

    override suspend fun setAccountNotesSettings(notesSettings: NotesSettings): EmptyResult<DataError> {
        val result = localDataSource.setAccountNotesSettings(notesSettings)
        if(result !is Result.Success) {
            return result.asEmptyDataResult()
        }
        return Result.Success(Unit)
    }
}