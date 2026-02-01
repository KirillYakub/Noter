package com.kiras.noter.domain.accounts.repository

import com.kiras.noter.domain.accounts.model.Account
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.Result
import kotlinx.coroutines.flow.Flow

typealias AccountId = String

interface AccountsLocalDataSource {
    fun getAccounts(): Flow<List<Account>>
    suspend fun registerAccount(account: Account): Result<AccountId, DataError.Local>
    suspend fun loginAccount(email: String, passwordHash: String): Result<AccountId, DataError.Local>
    suspend fun getAccount(id: AccountId): Account?
    suspend fun deleteAccount(id: AccountId)
}