package com.kiras.noter.domain

interface IdProvider {
    fun newId(): String
}