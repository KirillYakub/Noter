package com.kiras.noter.domain.accounts.repository.settings

import com.kiras.noter.domain.notes.model.settings.NotesSettings
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow

interface AccountNotesSettingsRepository {
    suspend fun getAccountNotesSettingsAsFlow(): Flow<NotesSettings>
    suspend fun getAccountNotesSettings(): NotesSettings
    suspend fun setAccountNotesSettings(notesSettings: NotesSettings): EmptyResult<DataError>
}