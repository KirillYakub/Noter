package com.kiras.noter.domain.util.date

import com.kiras.noter.domain.notes.model.CalendarDay
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime

fun lastMonthToToday(): List<CalendarDay> {
    val today = ZonedDateTime.now()
    val startDate = today.minusMonths(1).toLocalDate()
    val endDate = today.toLocalDate()

    return generateSequence(startDate) { date ->
        if (date.isBefore(endDate)) date.plusDays(1) else null
    }.map { localDate ->
        CalendarDay(localDate.atStartOfDay(today.zone))
    }.toList()
}

fun LocalDate.toEpochDayRange(): Pair<Long, Long> {
    val zoneId: ZoneId = ZoneId.systemDefault()
    val start = atStartOfDay(zoneId).toInstant().toEpochMilli()
    val endExclusive = plusDays(1).atStartOfDay(zoneId).toInstant().toEpochMilli()
    return start to (endExclusive - 1)
}