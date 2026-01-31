package com.kiras.noter.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel(

) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.OnLoginClick -> {

            }
            LoginAction.OnTogglePasswordVisibility -> {

            }
            else -> Unit
        }
    }
}