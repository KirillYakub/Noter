package com.kiras.noter.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kiras.noter.database.entity.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountsDao {

    @Query("SELECT * FROM accounts")
    fun getAllAccounts(): Flow<List<AccountEntity>>

    @Upsert
    suspend fun registerAccount(accountEntity: AccountEntity)

    @Query("SELECT * FROM accounts WHERE email = :email AND passwordHash = :passwordHash LIMIT 1")
    suspend fun loginAccount(email: String, passwordHash: String): AccountEntity?

    @Query("SELECT * FROM accounts WHERE id = :id LIMIT 1")
    suspend fun getAccountById(id: String): AccountEntity

    @Query("DELETE FROM accounts WHERE id = :id")
    suspend fun deleteAccountById(id: String)
}