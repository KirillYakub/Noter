package com.kiras.noter.presentation.login

sealed interface LoginEvent {
    data object LoginSuccess: LoginEvent
    data class Error(val error: String): LoginEvent
}