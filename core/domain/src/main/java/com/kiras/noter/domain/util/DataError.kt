package com.kiras.noter.domain.util

sealed interface DataError: Error {
    enum class Network: DataError {

    }
    enum class Local: DataError {
        UNAUTHORIZED,
        CONFLICT,
        DISC_FULL
    }
}