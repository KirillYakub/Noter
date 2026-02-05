package com.kiras.noter.domain.use_case

import com.kiras.noter.domain.accounts.repository.AuthActiveSessionStorage

class LogoutUseCase(
    private val authActiveSessionStorage: AuthActiveSessionStorage
) {
    suspend fun logout() {
        authActiveSessionStorage.set(null)
    }
}