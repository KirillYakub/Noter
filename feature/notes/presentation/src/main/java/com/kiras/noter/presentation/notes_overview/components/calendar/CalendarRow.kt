package com.kiras.noter.presentation.notes_overview.components.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kiras.noter.presentation.notes_overview.model.CalendarDayUi

@Composable
fun CalendarRow(
    days: List<CalendarDayUi>,
    onDayClick: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .padding(top = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(items = days, key = { it.id }) { day ->
            CalendarItem(
                isSelected = day.isSelected,
                dayOfWeek = day.dayOfWeek,
                dayOfMonth = day.dayOfMonth,
                month = day.month,
                onClick = { onDayClick(day.id) }
            )
        }
    }
}