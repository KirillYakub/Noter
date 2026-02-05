package com.kiras.noter.presentation.accounts

sealed interface AccountsOverviewAction {
    data object OnBackClick: AccountsOverviewAction
    data object OnAccountDelete: AccountsOverviewAction
    data class OnAccountClick(val id: String): AccountsOverviewAction
}