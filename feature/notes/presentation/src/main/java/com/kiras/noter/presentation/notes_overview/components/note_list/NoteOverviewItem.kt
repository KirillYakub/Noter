package com.kiras.noter.presentation.notes_overview.components.note_list

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.Grey1
import com.kiras.noter.designsystem.Grey5
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.PinkDarkTheme
import com.kiras.noter.designsystem.PinkLightTheme
import com.kiras.noter.designsystem.getDefaultColor
import com.kiras.noter.designsystem.getDefaultLineColor
import com.kiras.noter.domain.notes.model.settings.NotesStyleType
import com.kiras.noter.presentation.R

@Composable
fun NoteOverviewItem(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    date: String,
    color: Color,
    isNotesStyleLine: Boolean,
    isColorDefault: Boolean,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onCopyClick: () -> Unit
) {
    val density = LocalDensity.current
    var colorLineHeightDp by remember { mutableStateOf(0.dp) }

    var showDropDown by remember { mutableStateOf(false) }

    val useDefaultColor = isColorDefault || isNotesStyleLine
    val textColor =
        if(useDefaultColor && isSystemInDarkTheme()) Color.White else Color.Black
    val lineColor = if(color == getDefaultColor) getDefaultLineColor else color

    OutlinedCard(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = if(useDefaultColor) 1.dp else 0.dp,
            color = if(useDefaultColor) {
                if(isSystemInDarkTheme()) Color.White else Color.Black.copy(alpha = 0.6f)
            } else {
                Color.Transparent
            }
        ),
        colors = CardDefaults.cardColors(
            containerColor = when {
                !useDefaultColor -> color
                isSystemInDarkTheme() -> Color.Black
                else -> Color.White
            }
        ),
        modifier = modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = { showDropDown = true }
            )
    ) {
        Row(
            modifier = Modifier.padding(15.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if(isNotesStyleLine) {
                Spacer(
                    modifier = Modifier
                        .size(
                            width = 3.dp,
                            height = colorLineHeightDp
                        )
                        .background(lineColor)
                        .clip(CircleShape)
                )
            }
            Column(
                modifier = Modifier.onGloballyPositioned {
                    colorLineHeightDp = with(density) { it.size.height.toDp() }
                },
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (title.isNotBlank()) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = textColor,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                if (content.isNotBlank()) {
                    Text(
                        text = content,
                        style = MaterialTheme.typography.bodyMedium,
                        color = textColor,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall,
                    color = textColor,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        DropdownMenu(
            expanded = showDropDown,
            onDismissRequest = { showDropDown = false },
            containerColor = if(isSystemInDarkTheme()) Grey5 else Grey1,
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.create_copy),
                        style = MaterialTheme.typography.titleSmall,
                        color = (if(isSystemInDarkTheme()) Color.White else Color.Black).copy(alpha = 0.8f)
                    )
                },
                onClick = {
                    showDropDown = false
                    onCopyClick()
                }
            )
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
            isNotesStyleLine = true,
            date = "2024-02-02 14:02",
            onClick = {},
            onDeleteClick = {},
            onCopyClick = {}
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun NoteOverviewItemPreviewNight() {
    NoterTheme {
        NoteOverviewItem(
            title = "Lorem ipsum",
            content = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad",
            color = PinkDarkTheme,
            isColorDefault = false,
            isNotesStyleLine = true,
            date = "2024-02-02 14:02",
            onClick = {},
            onDeleteClick = {},
            onCopyClick = {}
        )
    }
}