package com.kiras.noter.presentation.registration

import androidx.compose.foundation.text.input.TextFieldState
import com.kiras.noter.domain.PasswordValidationState
import com.kiras.noter.domain.accounts.model.AccountIcon

data class RegisterState(
    val accountIcon: AccountIcon = AccountIcon.ICON_1,
    val email: TextFieldState = TextFieldState(),
    val isEmailValid: Boolean = false,
    val name: TextFieldState = TextFieldState(),
    val isNameValid: Boolean = false,
    val password: TextFieldState = TextFieldState(),
    val isPasswordValid: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val isRegistering: Boolean = false,
    val canRegister: Boolean = false
)
