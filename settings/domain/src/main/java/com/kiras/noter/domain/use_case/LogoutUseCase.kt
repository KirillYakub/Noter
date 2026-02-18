package com.kiras.noter.domain.use_case

import com.kiras.noter.domain.accounts.model.Account
import com.kiras.noter.domain.accounts.repository.AccountsRepository
import com.kiras.noter.domain.accounts.repository.AuthActiveSessionStorage

class LogoutUseCase(
    private val accountsRepository: AccountsRepository,
    private val authActiveSessionStorage: AuthActiveSessionStorage
) {
    suspend fun logout() {
        authActiveSessionStorage.set(null)
    }

    suspend fun getAccountById(): Account {
        val accountId = authActiveSessionStorage.get()!!.userId
        return accountsRepository.getAccount(accountId)
    }
}