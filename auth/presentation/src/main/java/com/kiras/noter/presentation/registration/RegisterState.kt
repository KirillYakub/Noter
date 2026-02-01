package com.kiras.noter.presentation.registration

import androidx.compose.foundation.text.input.TextFieldState
import com.kiras.noter.domain.PasswordValidationState
import com.kiras.noter.domain.accounts.model.AuthIcon

data class RegisterState(
    val authIcon: AuthIcon = AuthIcon.ICON_1,
    val email: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = false,
    val password: TextFieldState = TextFieldState(),
    val isPasswordValid: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val isRegistering: Boolean = false,
    val canRegister: Boolean = false
)
