package com.kiras.noter.domain.accounts.repository.settings

import com.kiras.noter.domain.notes.model.settings.NotesSettings
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.Result
import kotlinx.coroutines.flow.Flow

typealias NotesSettingsId = String

interface AccountNotesSettingsLocalDataSource {
    fun getAccountNotesSettingsAsFlow(ownerAccountId: String): Flow<NotesSettings>
    suspend fun getAccountNotesSettings(ownerAccountId: String): NotesSettings
    suspend fun setAccountNotesSettings(notesSettings: NotesSettings): Result<NotesSettingsId, DataError.Local>
}