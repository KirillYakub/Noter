package com.kiras.noter.presentation.registration

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel

fun TextFieldState.textAsFlow() = snapshotFlow { text }

class RegisterViewModel : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnAuthIconChange -> {

            }
            RegisterAction.OnRegisterClick -> {}
            RegisterAction.OnTogglePasswordVisibilityClick -> {}
            else -> Unit
        }
    }
}