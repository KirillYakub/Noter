package com.kiras.noter.presentation.accounts

import com.kiras.noter.presentation.accounts.model.AccountUi

data class AccountsOverviewState(
    val accounts: List<AccountUi> = emptyList(),
)
