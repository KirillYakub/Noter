package com.kiras.noter.domain.repository

import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.EmptyResult

interface AuthRepository {
    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}