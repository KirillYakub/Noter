package com.kiras.noter.domain.use_case

import com.kiras.noter.domain.ClockProvider
import com.kiras.noter.domain.IdProvider
import com.kiras.noter.domain.accounts.model.Account
import com.kiras.noter.domain.accounts.model.AccountIcon
import com.kiras.noter.domain.accounts.repository.AccountsRepository
import com.kiras.noter.domain.repository.PasswordHasher
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.EmptyResult

class AuthUseCase(
    private val idProvider: IdProvider,
    private val clockProvider: ClockProvider,
    private val passwordHasher: PasswordHasher,
    private val accountsRepository: AccountsRepository
) {
    suspend fun login(email: String, password: String): EmptyResult<DataError> {
        val passwordHash = passwordHasher.hash(password)
        return accountsRepository.loginAccount(email, passwordHash)
    }

    suspend fun register(email: String, password: String, name: String, icon: AccountIcon): EmptyResult<DataError> {
        val passwordHash = passwordHasher.hash(password)
        val account = Account(
            id = idProvider.newId(),
            email = email,
            icon = icon,
            name = name,
            passwordHash = passwordHash,
            lastSignIn = clockProvider.now()
        )
        return accountsRepository.registerAccount(account)
    }
}