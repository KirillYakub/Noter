package com.kiras.noter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiras.noter.domain.SessionStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val sessionStorage: SessionStorage
): ViewModel() {

    var state by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(isCheckingAuth = true)
            state = state.copy(
                isCheckingAuth = false,
                isLoggedIn = sessionStorage.get() != null
            )
        }
    }
}