package com.kiras.noter.data.offline_first.account

import com.kiras.noter.domain.accounts.model.AuthInfo
import com.kiras.noter.domain.accounts.repository.AuthActiveSessionStorage
import com.kiras.noter.domain.accounts.model.Account
import com.kiras.noter.domain.accounts.repository.AccountId
import com.kiras.noter.domain.accounts.repository.AccountsLocalDataSource
import com.kiras.noter.domain.accounts.repository.AccountsRepository
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.EmptyResult
import com.kiras.noter.domain.util.Result
import com.kiras.noter.domain.util.asEmptyDataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class OfflineFirstAccountRepositoryImpl(
    private val localDataSource: AccountsLocalDataSource,
    private val applicationScope: CoroutineScope,
    private val authActiveSessionStorage: AuthActiveSessionStorage
): AccountsRepository {

    override fun getAccounts(): Flow<List<Account>> {
        return localDataSource.getAccounts()
    }

    override suspend fun registerAccount(account: Account): EmptyResult<DataError> {
        val result = localDataSource.registerAccount(account)
        if(result !is Result.Success) {
            return result.asEmptyDataResult()
        }
        authActiveSessionStorage.set(authInfo = AuthInfo(userId = result.data))
        return Result.Success(Unit)
    }

    override suspend fun loginAccount(
        email: String,
        passwordHash: String,
    ): EmptyResult<DataError> {
        val result = localDataSource.loginAccount(email, passwordHash)
        if(result !is Result.Success) {
            return result.asEmptyDataResult()
        }
        authActiveSessionStorage.set(authInfo = AuthInfo(userId = result.data))
        return Result.Success(Unit)
    }

    override suspend fun getAccount(id: AccountId): Account {
        return localDataSource.getAccount(id)
    }

    override suspend fun deleteAccount(id: AccountId) {
        if(id == authActiveSessionStorage.get()?.userId) {
            authActiveSessionStorage.set(null)
        }
        localDataSource.deleteAccount(id)
    }
}