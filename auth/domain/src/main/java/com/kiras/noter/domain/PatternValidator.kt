package com.kiras.noter.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}