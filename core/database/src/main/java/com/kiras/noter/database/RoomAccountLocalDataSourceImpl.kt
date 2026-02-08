package com.kiras.noter.database

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteFullException
import androidx.room.withTransaction
import com.kiras.noter.database.dao.AccountNotesSettingsDao
import com.kiras.noter.database.dao.AccountsDao
import com.kiras.noter.database.entity.AccountNotesSettingsEntity
import com.kiras.noter.database.mappers.toAccount
import com.kiras.noter.database.mappers.toAccountEntity
import com.kiras.noter.domain.accounts.model.Account
import com.kiras.noter.domain.accounts.repository.AccountId
import com.kiras.noter.domain.accounts.repository.AccountsLocalDataSource
import com.kiras.noter.domain.notes.model.settings.NotesDisplayType
import com.kiras.noter.domain.notes.model.settings.NotesSortType
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomAccountLocalDataSourceImpl(
    private val notesDatabase: NotesDatabase
): AccountsLocalDataSource {

    private val accountDao = notesDatabase.accountsDao
    private val settingsDao = notesDatabase.notesSettingsDao

    override fun getAccounts(): Flow<List<Account>> {
        return accountDao.getAllAccounts().map { accounts ->
            accounts.map { it.toAccount() }
        }
    }

    override suspend fun registerAccount(account: Account): Result<AccountId, DataError.Local> {
        return try {
            val accountAsEntity = account.toAccountEntity()
            notesDatabase.withTransaction {
                accountDao.registerAccount(accountAsEntity)
                settingsDao.updateAccountNotesSettings(accountNotesSettingsEntity =
                    AccountNotesSettingsEntity(
                        ownerAccountId = accountAsEntity.id,
                        isDateSearchEnabled = false,
                        displayType = NotesDisplayType.GRID,
                        sortType = NotesSortType.DATE
                    )
                )
            }
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
    ): Result<AccountId, DataError.Local> {
        val account = accountDao.loginAccount(email, passwordHash)?.toAccount()
        return if (account != null) {
            Result.Success(account.id)
        } else {
            Result.Error(DataError.Local.UNAUTHORIZED)
        }
    }

    override suspend fun getAccount(id: AccountId): Account? {
        return accountDao.getAccountById(id)?.toAccount()
    }

    override suspend fun deleteAccount(id: AccountId) {
        accountDao.deleteAccountById(id)
    }
}