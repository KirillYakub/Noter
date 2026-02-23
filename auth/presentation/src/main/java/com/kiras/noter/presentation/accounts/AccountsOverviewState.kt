package com.kiras.noter.presentation.accounts

import com.kiras.noter.presentation.accounts.model.AccountUi

data class AccountsOverviewState(
    val showContent: Boolean = false,
    val accounts: List<AccountUi> = emptyList(),
)
