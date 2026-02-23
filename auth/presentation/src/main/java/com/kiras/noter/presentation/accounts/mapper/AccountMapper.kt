package com.kiras.noter.presentation.accounts.mapper

import com.kiras.noter.domain.accounts.model.Account
import com.kiras.noter.presentation.accounts.model.AccountUi
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Account.toAccountUi(): AccountUi {
    val formatTimePattern = "dd.MM.yyyy HH:mm"
    val createTimeInLocalTime = lastSignIn
        .withZoneSameInstant(ZoneId.systemDefault())

    val formattedSignInTimeAsString = DateTimeFormatter
        .ofPattern(formatTimePattern)
        .format(createTimeInLocalTime)

    return AccountUi(
        id = id,
        name = name,
        icon = icon,
        email = email,
        lastSignIn = formattedSignInTimeAsString
    )
}