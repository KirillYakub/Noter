package com.kiras.noter.presentation.notes_overview.model

data class CalendarDayUi(
    val id: String,
    val dayOfWeek: String,
    val dayOfMonth: String,
    val month: String,
    val isSelected: Boolean
)