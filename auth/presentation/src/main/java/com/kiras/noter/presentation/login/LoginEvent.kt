package com.kiras.noter.presentation.login

import com.kiras.noter.ui.UiText

sealed interface LoginEvent {
    data object LoginSuccess: LoginEvent
    data class Error(val error: UiText): LoginEvent
}