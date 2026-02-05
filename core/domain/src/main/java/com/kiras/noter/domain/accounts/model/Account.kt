package com.kiras.noter.domain.accounts.model

import java.time.ZonedDateTime

data class Account(
    val id: String,
    val email: String,
    val name: String,
    val passwordHash: String,
    val icon: AccountIcon,
    val lastSignIn: ZonedDateTime
)
