package com.kiras.noter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiras.noter.domain.accounts.repository.AuthActiveSessionStorage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(authActiveSessionStorage: AuthActiveSessionStorage): ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    init {
        authActiveSessionStorage.getAsFlow()
            .onEach { authInfo ->
                state = state.copy(
                    isLoggedIn = authInfo != null,
                    isCheckingAuth = false
                )
            }
            .launchIn(viewModelScope)
    }
}