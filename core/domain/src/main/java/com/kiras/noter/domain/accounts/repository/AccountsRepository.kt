package com.kiras.noter.domain.accounts.repository

import com.kiras.noter.domain.accounts.model.Account
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow

interface AccountsRepository {
    fun getAccounts(): Flow<List<Account>>
    suspend fun registerAccount(account: Account): EmptyResult<DataError>
    suspend fun loginAccount(email: String, passwordHash: String): EmptyResult<DataError>
    suspend fun getAccount(id: AccountId): Account?
    suspend fun deleteAccount(id: AccountId)
}