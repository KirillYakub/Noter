package com.kiras.noter.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.getCalendarSelectedItemColor
import com.kiras.noter.designsystem.getCalendarUnselectedItemBorderColor
import com.kiras.noter.designsystem.getCalendarUnselectedItemColor
import com.kiras.noter.designsystem.getCalenderSelectedItemBorderColor

@Composable
fun CalendarItem(
    isSelected: Boolean,
    dayOfWeek: String,
    dayOfMonth: String,
    month: String,
    onClick: () -> Unit
) {
    OutlinedCard(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if(isSelected) getCalenderSelectedItemBorderColor else getCalendarUnselectedItemBorderColor
        ),
        colors = CardDefaults.cardColors(
            containerColor = if(isSelected) getCalendarSelectedItemColor else getCalendarUnselectedItemColor
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dayOfWeek,
                style = MaterialTheme.typography.labelSmall,
                color = if(isSelected || isSystemInDarkTheme()) Color.White else Color.Black
            )
            Text(
                text = dayOfMonth,
                style = MaterialTheme.typography.headlineMedium,
                color = if(isSelected || isSystemInDarkTheme()) Color.White else Color.Black
            )
            Text(
                text = month,
                style = MaterialTheme.typography.labelSmall,
                color = if(isSelected || isSystemInDarkTheme()) Color.White else Color.Black
            )
        }
    }
}

@Preview
@Composable
fun CalendarItemPreview() {
    NoterTheme {
        CalendarItem(
            isSelected = true,
            dayOfWeek = "Mon",
            dayOfMonth = "1",
            month = "Jan",
            onClick = {}
        )
    }
}