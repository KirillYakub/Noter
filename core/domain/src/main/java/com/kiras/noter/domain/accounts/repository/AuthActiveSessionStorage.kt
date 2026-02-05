package com.kiras.noter.domain.accounts.repository

import com.kiras.noter.domain.accounts.model.AuthInfo

interface AuthActiveSessionStorage {
    suspend fun get(): AuthInfo?
    suspend fun set(authInfo: AuthInfo?)
}