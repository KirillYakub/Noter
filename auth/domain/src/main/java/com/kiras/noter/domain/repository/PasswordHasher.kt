package com.kiras.noter.domain.repository

interface PasswordHasher {
    fun hash(password: String): String
}