package com.kiras.noter.presentation.registration

import com.kiras.noter.domain.model.AuthIcon

sealed interface RegisterAction {
    data class OnAuthIconChange(val authIcon: AuthIcon): RegisterAction
    data object OnTogglePasswordVisibilityClick: RegisterAction
    data object OnLoginClick: RegisterAction
    data object OnRegisterClick: RegisterAction
}