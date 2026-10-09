package com.kiras.noter.presentation.notes_overview.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.kiras.noter.designsystem.BackIcon
import com.kiras.noter.designsystem.getCalendarSelectedItemColor
import com.kiras.noter.designsystem.getFolderSelectedItemColor
import com.kiras.noter.designsystem.getFolderSelectedItemBorderColor
import com.kiras.noter.presentation.R
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun FullCalendarDialog(
    selectedDayId: String?,
    onDaySelected: (String?) -> Unit,
    onDismiss: () -> Unit
) {
    var displayedYearMonth by remember {
        mutableStateOf(
            selectedDayId?.let { LocalDate.parse(it) }?.let { YearMonth.from(it) } ?: YearMonth.now()
        )
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(if (isSystemInDarkTheme()) Color.Black else Color.White),
            color = if (isSystemInDarkTheme()) Color.Black else Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        IconButton(onClick = onDismiss) {
                            Icon(
                                imageVector = BackIcon,
                                contentDescription = stringResource(R.string.back),
                                tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                            )
                        }
                        Text(
                            text = "${displayedYearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${displayedYearMonth.year}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = if (isSystemInDarkTheme()) Color.White else Color.Black
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        TextButton(onClick = { displayedYearMonth = displayedYearMonth.minusMonths(1) }) {
                            Text("<", color = if (isSystemInDarkTheme()) Color.White else Color.Black)
                        }
                        TextButton(onClick = { displayedYearMonth = displayedYearMonth.plusMonths(1) }) {
                            Text(">", color = if (isSystemInDarkTheme()) Color.White else Color.Black)
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                    daysOfWeek.forEach { day ->
                        Text(
                            text = day,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
                            modifier = Modifier.width(40.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                val firstDayOfMonth = displayedYearMonth.atDay(1)
                val daysInMonth = displayedYearMonth.lengthOfMonth()
                val dayOfWeekIndex = firstDayOfMonth.dayOfWeek.value
                val totalCells = daysInMonth + (dayOfWeekIndex - 1)
                val rows = (totalCells + 6) / 7

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (row in 0 until rows) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            for (col in 0 until 7) {
                                val cellIndex = row * 7 + col
                                val dayNumber = cellIndex - (dayOfWeekIndex - 2)
                                if (dayNumber in 1..daysInMonth) {
                                    val date = displayedYearMonth.atDay(dayNumber)
                                    val dateString = date.toString()
                                    val isSelected = dateString == selectedDayId

                                    Box(
                                        modifier = Modifier
                                            .size(44.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(
                                                color = if (isSelected) getCalendarSelectedItemColor else Color.Transparent
                                            )
                                            .border(
                                                width = 1.dp,
                                                color = Color.Transparent,
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .clickable {
                                                onDaySelected(if (isSelected) null else dateString)
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = dayNumber.toString(),
                                            style = MaterialTheme.typography.bodyLarge,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            color = if (isSelected || isSystemInDarkTheme()) Color.White else Color.Black
                                        )
                                    }
                                }
                                else {
                                    Spacer(modifier = Modifier.size(42.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
