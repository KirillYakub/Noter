package com.kiras.noter.notes.presentation.notes_overview.components.note_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.Grey1
import com.kiras.noter.designsystem.Grey2
import com.kiras.noter.designsystem.Grey3
import com.kiras.noter.designsystem.Grey4
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.PinkLightTheme
import com.kiras.noter.notes.R

@Composable
fun NoteOverviewItem(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    color: Color,
    isColorDefault: Boolean,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var showDropDown by remember { mutableStateOf(false) }
    OutlinedCard(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = if(isColorDefault) 1.dp else 0.dp,
            color = if(isColorDefault) {
                if(isSystemInDarkTheme()) Color.White else Color.Black.copy(alpha = 0.6f)
            } else {
                Color.Transparent
            }
        ),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        modifier = modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = { showDropDown = true }
            )
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if(title.isNotBlank()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if(isColorDefault && isSystemInDarkTheme()) Color.White else Color.Black,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if(content.isNotBlank()) {
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodySmall,
                    color = if(isColorDefault && isSystemInDarkTheme()) Color.White else Color.Black,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        DropdownMenu(
            expanded = showDropDown,
            onDismissRequest = { showDropDown = false },
            containerColor = if(isSystemInDarkTheme()) Grey4 else Grey1,
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.delete),
                        style = MaterialTheme.typography.titleSmall,
                        color = (if(isSystemInDarkTheme()) Color.White else Color.Black).copy(alpha = 0.8f)
                    )
                },
                onClick = {
                    showDropDown = false
                    onDeleteClick()
                }
            )
        }
    }
}

@Preview
@Composable
fun NoteOverviewItemPreview() {
    NoterTheme {
        NoteOverviewItem(
            title = "Lorem ipsum",
            content = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad",
            color = PinkLightTheme,
            isColorDefault = false,
            onClick = {},
            onDeleteClick = {}
        )
    }
}