package com.kiras.noter.presentation.intro

sealed interface IntroAction {
    data object OnAccountsClick: IntroAction
    data object OnLoginClick: IntroAction
    data object OnRegisterClick: IntroAction
}