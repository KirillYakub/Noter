package com.kiras.noter.domain.repository

import java.time.ZonedDateTime

interface ClockProvider {
    fun now(): ZonedDateTime
}
