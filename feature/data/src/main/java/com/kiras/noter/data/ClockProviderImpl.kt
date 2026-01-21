package com.kiras.noter.data

import com.kiras.noter.domain.repository.ClockProvider
import java.time.ZonedDateTime

class ClockProviderImpl : ClockProvider {
    override fun now(): ZonedDateTime = ZonedDateTime.now()
}