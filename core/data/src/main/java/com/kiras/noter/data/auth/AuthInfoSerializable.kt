package com.kiras.noter.data.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoSerializable (
    val userId: String
)