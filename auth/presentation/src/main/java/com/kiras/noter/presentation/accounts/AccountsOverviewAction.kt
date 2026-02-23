package com.kiras.noter.presentation.accounts

sealed interface AccountsOverviewAction {
    data object OnBackClick: AccountsOverviewAction
    data class OnAccountDelete(val id: String): AccountsOverviewAction
    data class OnAccountClick(val email: String): AccountsOverviewAction
}