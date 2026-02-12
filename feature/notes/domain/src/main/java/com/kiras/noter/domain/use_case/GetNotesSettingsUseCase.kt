package com.kiras.noter.domain.use_case

import com.kiras.noter.domain.accounts.repository.settings.AccountNotesSettingsRepository
import com.kiras.noter.domain.notes.model.settings.NotesSettings
import kotlinx.coroutines.flow.Flow

class GetNotesSettingsUseCase(
    private val accountNotesSettingsStorage: AccountNotesSettingsRepository
) {
    fun getNotesSettingsAsFlow(): Flow<NotesSettings> {
        return accountNotesSettingsStorage.getAccountNotesSettingsAsFlow()
    }

    suspend fun getNotesSettings(): NotesSettings {
        return accountNotesSettingsStorage.getAccountNotesSettings()
    }
}