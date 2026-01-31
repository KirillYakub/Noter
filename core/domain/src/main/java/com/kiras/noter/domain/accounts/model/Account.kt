package com.kiras.noter.domain.accounts.model

import java.security.MessageDigest
import java.time.ZonedDateTime

data class Account(
    val id: String,
    val email: String,
    val passwordHash: String,
    val lastSignIn: ZonedDateTime
)
