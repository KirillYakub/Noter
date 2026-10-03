package com.kiras.noter.domain.accounts.repository

import com.kiras.noter.domain.accounts.model.AuthInfo
import kotlinx.coroutines.flow.Flow

interface AuthActiveSessionStorage {
    fun getAsFlow(): Flow<AuthInfo?>
    suspend fun get(): AuthInfo?
    suspend fun set(authInfo: AuthInfo?)
}