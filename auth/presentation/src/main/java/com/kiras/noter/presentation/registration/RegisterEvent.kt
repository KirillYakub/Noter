package com.kiras.noter.presentation.registration

sealed interface RegisterEvent {
    data object RegistrationSuccess: RegisterEvent
    data class Error(val error: String): RegisterEvent
}