package com.kiras.noter.presentation.registration

import com.kiras.noter.domain.accounts.model.AccountIcon

sealed interface RegisterAction {
    data class OnAuthIconChange(val accountIcon: AccountIcon): RegisterAction
    data object OnTogglePasswordVisibilityClick: RegisterAction
    data object OnLoginClick: RegisterAction
    data object OnRegisterClick: RegisterAction
}