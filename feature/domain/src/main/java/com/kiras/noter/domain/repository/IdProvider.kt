package com.kiras.noter.domain.repository

interface IdProvider {
    fun newId(): String
}
