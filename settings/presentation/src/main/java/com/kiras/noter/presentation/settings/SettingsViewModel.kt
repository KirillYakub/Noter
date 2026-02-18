package com.kiras.noter.presentation.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiras.noter.domain.accounts.repository.AccountsRepository
import com.kiras.noter.domain.use_case.LogoutUseCase
import com.kiras.noter.domain.use_case.NotesSettingsUseCase
import com.kiras.noter.presentation.settings.mapper.toNotesSettings
import com.kiras.noter.presentation.settings.mapper.toNotesSettingsUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val notesSettingsUseCase: NotesSettingsUseCase,
    private val applicationScope: CoroutineScope
) : ViewModel() {

    var state by mutableStateOf(SettingsState())
        private set

    private val eventChannel = Channel<Unit>()
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            state = state.copy(
                showContent = true,
                accountNotesSettings = notesSettingsUseCase.getNotesSettings()
                    .toNotesSettingsUi()
                    .copy(
                        accountIcon = logoutUseCase.getAccountById().icon
                    )
            )
        }
    }

    fun onAction(action: SettingsActions) {
        when (action) {
            SettingsActions.OnLogoutClick -> logout()
            is SettingsActions.OnDateSearchEnableChange -> {
                state = state.copy(accountNotesSettings =
                    state.accountNotesSettings.copy(isDateSearchEnabled = action.enable)
                )
                updateNote(state)
            }
            is SettingsActions.OnNotesDisplayChange -> {
                state = state.copy(accountNotesSettings =
                    state.accountNotesSettings.copy(displayType = action.displayType)
                )
                updateNote(state)
            }
            is SettingsActions.OnNotesSortChange -> {
                state = state.copy(accountNotesSettings =
                    state.accountNotesSettings.copy(sortType = action.sortType)
                )
                updateNote(state)
            }
            is SettingsActions.OnNotesStyleChange -> {
                state = state.copy(accountNotesSettings =
                    state.accountNotesSettings.copy(notesStyle = action.notesStyle)
                )
                updateNote(state)
            }
            else -> Unit
        }
    }

    private fun updateNote(state: SettingsState) {
        applicationScope.launch {
            notesSettingsUseCase.setNotesSettings(state.accountNotesSettings.toNotesSettings())
        }
    }

    private fun logout() {
        applicationScope.launch {
            logoutUseCase.logout()
            eventChannel.send(Unit)
        }
    }
 }