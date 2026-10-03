package com.kiras.noter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiras.noter.domain.accounts.repository.AuthActiveSessionStorage
import kotlinx.coroutines.launch

class MainViewModel(
    private val authActiveSessionStorage: AuthActiveSessionStorage
): ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            val isLoggedIn = authActiveSessionStorage.get() != null
            state = state.copy(
                isLoggedIn = isLoggedIn,
                isCheckingAuth = false
            )
        }
    }
}