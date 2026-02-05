package com.kiras.noter.database.mappers

import com.kiras.noter.database.entity.AccountEntity
import com.kiras.noter.domain.accounts.model.Account
import java.time.Instant
import java.time.ZoneId

fun AccountEntity.toAccount(): Account {
    return Account(
        id = id,
        email = email,
        passwordHash = passwordHash,
        name = name,
        icon = icon,
        lastSignIn = Instant.ofEpochMilli(lastSignIn).atZone(ZoneId.systemDefault()),
    )
}

fun Account.toAccountEntity(): AccountEntity {
    return AccountEntity(
        id = id,
        email = email,
        passwordHash = passwordHash,
        name = name,
        icon = icon,
        lastSignIn = lastSignIn.toInstant().toEpochMilli()
    )
}