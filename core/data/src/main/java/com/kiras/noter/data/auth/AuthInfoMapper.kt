package com.kiras.noter.data.auth

import com.kiras.noter.domain.accounts.model.AuthInfo

fun AuthInfoSerializable.toAuthInfo(): AuthInfo {
    return AuthInfo(userId = userId)
}

fun AuthInfo.toAuthInfoSerializable(): AuthInfoSerializable {
    return AuthInfoSerializable(userId = userId)
}