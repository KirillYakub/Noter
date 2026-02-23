package com.kiras.noter.presentation.util

import kotlinx.serialization.Serializable

@Serializable
data class Login(val email: String? = null)

@Serializable
data object Registration

@Serializable
data object Intro

@Serializable
data object Accounts