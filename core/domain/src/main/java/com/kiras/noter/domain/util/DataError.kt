package com.kiras.noter.domain.util

sealed interface DataError: Error {
    enum class Network: DataError {
        NO_INTERNET,
        SERIALIZATION,
        REQUEST_TIMEOUT,
        CONFLICT,
        SERVER_ERROR,
        UNKNOWN
    }
    enum class Local: DataError {
        CONFLICT,
        DISC_FULL
    }
}