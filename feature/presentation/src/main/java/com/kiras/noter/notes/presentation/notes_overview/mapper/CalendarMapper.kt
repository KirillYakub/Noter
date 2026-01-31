package com.kiras.noter.notes.presentation.notes_overview.mapper

import com.kiras.noter.domain.notes.model.CalendarDay
import com.kiras.noter.notes.presentation.notes_overview.model.CalendarDayUi
import java.time.format.DateTimeFormatter
import java.util.Locale

fun CalendarDay.toCalendarDayUi(isSelected: Boolean): CalendarDayUi {
    val formatterDay = DateTimeFormatter.ofPattern("EEE", Locale.getDefault())
    val formatterMonth = DateTimeFormatter.ofPattern("MMM", Locale.getDefault())

    return CalendarDayUi(
        id = date.toLocalDate().toString(),
        dayOfWeek = date.format(formatterDay),
        dayOfMonth = date.dayOfMonth.toString(),
        month = date.format(formatterMonth),
        isSelected = isSelected
    )
}