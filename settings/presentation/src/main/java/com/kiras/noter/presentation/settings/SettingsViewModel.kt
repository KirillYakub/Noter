package com.kiras.noter.presentation.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiras.noter.domain.use_case.LogoutUseCase
import com.kiras.noter.domain.use_case.NotesSettingsUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val logoutUseCase: LogoutUseCase,
    private val notesSettingsUseCase: NotesSettingsUseCase,
) : ViewModel() {

    var state by mutableStateOf(SettingsState())
        private set

    private val eventChannel = Channel<Unit>(
        capacity = Channel.BUFFERED,
    )
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            state = state.copy(
                accountNotesSettings = notesSettingsUseCase.getNotesSettings(),
                isLoading = false
            )
        }
    }

    fun onAction(action: SettingsActions) {
        when (action) {
            SettingsActions.OnLogoutClick -> logout()
            is SettingsActions.OnDateSearchEnableChange -> {
                state = state.copy(accountNotesSettings =
                    state.accountNotesSettings!!.copy(isDateSearchEnabled = action.enable)
                )
                updateNote(state)
            }
            is SettingsActions.OnNotesDisplayChange -> {
                state = state.copy(accountNotesSettings =
                    state.accountNotesSettings!!.copy(displayType = action.displayType)
                )
                updateNote(state)
            }
            is SettingsActions.OnNotesSortChange -> {
                state = state.copy(accountNotesSettings =
                    state.accountNotesSettings!!.copy(sortType = action.sortType)
                )
                updateNote(state)
            }
            else -> Unit
        }
    }

    private fun updateNote(state: SettingsState) {
        viewModelScope.launch {
            notesSettingsUseCase.setNotesSettings(state.accountNotesSettings!!)
        }
    }

    private fun logout() {
        viewModelScope.launch {
            logoutUseCase.logout()
            eventChannel.send(Unit)
        }
    }
 }