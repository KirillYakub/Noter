package com.kiras.noter.database

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteFullException
import com.kiras.noter.database.dao.AccountsDao
import com.kiras.noter.database.mappers.toAccount
import com.kiras.noter.database.mappers.toAccountEntity
import com.kiras.noter.domain.accounts.model.Account
import com.kiras.noter.domain.accounts.repository.AccountId
import com.kiras.noter.domain.accounts.repository.AccountsLocalDataSource
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomAccountLocalDataSourceImpl(
    private val accountDao: AccountsDao
): AccountsLocalDataSource {

    override fun getAccounts(): Flow<List<Account>> {
        return accountDao.getAllAccounts().map { accounts ->
            accounts.map { it.toAccount() }
        }
    }

    override suspend fun registerAccount(account: Account): Result<AccountId, DataError.Local> {
        return try {
            val accountAsEntity = account.toAccountEntity()
            accountDao.registerAccount(accountAsEntity)
            Result.Success(accountAsEntity.id)
        } catch (_: SQLiteFullException) {
            Result.Error(DataError.Local.DISC_FULL)
        } catch (_: SQLiteConstraintException) {
            Result.Error(DataError.Local.CONFLICT)
        }
    }

    override suspend fun loginAccount(
        email: String,
        passwordHash: String,
    ): Account? {
        return accountDao.loginAccount(email, passwordHash)?.toAccount()
    }

    override suspend fun getAccount(id: AccountId): Account? {
        return accountDao.getAccountById(id)?.toAccount()
    }

    override suspend fun deleteAccount(id: AccountId) {
        accountDao.deleteAccountById(id)
    }
}