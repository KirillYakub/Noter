package com.kiras.noter.domain.use_case

import com.kiras.noter.domain.accounts.repository.settings.AccountNotesSettingsRepository
import com.kiras.noter.domain.notes.model.settings.NotesSettings

class NotesSettingsUseCase(
    private val accountNotesSettingsStorage: AccountNotesSettingsRepository
) {

    suspend fun getNotesSettings(): NotesSettings {
        return accountNotesSettingsStorage.getAccountNotesSettings()
    }

    suspend fun setNotesSettings(accountNotesSettings: NotesSettings) {
        accountNotesSettingsStorage.setAccountNotesSettings(accountNotesSettings)
    }
}