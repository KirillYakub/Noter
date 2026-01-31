package com.kiras.noter.data

import com.kiras.noter.domain.repository.PasswordHasher
import java.security.MessageDigest

class PasswordHasherImpl : PasswordHasher {

    override fun hash(password: String): String {
        return MessageDigest
            .getInstance("SHA-256")
            .digest(password.toByteArray())
            .joinToString("") { "%02x".format(it) }
    }
}