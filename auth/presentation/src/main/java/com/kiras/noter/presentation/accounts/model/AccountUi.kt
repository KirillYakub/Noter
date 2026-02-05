package com.kiras.noter.presentation.accounts.model

import com.kiras.noter.domain.accounts.model.AccountIcon

data class AccountUi(
    val id: String = "",
    val name: String = "",
    val icon: AccountIcon = AccountIcon.ICON_1,
    val lastSignIn: String = ""
)