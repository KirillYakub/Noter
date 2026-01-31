package com.kiras.noter.domain.use_case

import com.kiras.noter.domain.accounts.model.Account
import com.kiras.noter.domain.accounts.repository.AccountsRepository
import com.kiras.noter.domain.repository.PasswordHasher

class AuthUseCase(
    //private val authRepository: AuthRepository,
    private val passwordHasher: PasswordHasher,
    private val accountsRepository: AccountsRepository
) {
    suspend fun login(email: String, password: String): Account? {
        val passwordHash = passwordHasher.hash(password)
        return accountsRepository.loginAccount(email, passwordHash)
    }
}