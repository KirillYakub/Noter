package com.kiras.noter.domain

import java.time.ZonedDateTime

interface ClockProvider {
    fun now(): ZonedDateTime
}