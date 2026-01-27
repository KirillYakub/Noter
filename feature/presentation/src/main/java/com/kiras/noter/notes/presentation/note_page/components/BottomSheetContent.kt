package com.kiras.noter.notes.presentation.note_page.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.AlignCenterIcon
import com.kiras.noter.designsystem.AlignJustifyIcon
import com.kiras.noter.designsystem.AlignLeftIcon
import com.kiras.noter.designsystem.AlignRightIcon
import com.kiras.noter.designsystem.Grey4
import com.kiras.noter.domain.model.NoteColor
import com.kiras.noter.notes.R
import com.kiras.noter.notes.presentation.note_page.NotePageActions
import com.kiras.noter.notes.presentation.note_page.model.NoteAlignment
import com.kiras.noter.ui.getColorForUiTheme

@Composable
fun BottomSheetContent(
    onAction: (NotePageActions) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp,),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.alignment),
                style = MaterialTheme.typography.titleMedium,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                modifier = Modifier.weight(1f)
            )
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        onAction(NotePageActions.OnAlignChange(NoteAlignment.START))
                    }
                ) {
                    Icon(
                        imageVector = AlignLeftIcon,
                        contentDescription = stringResource(R.string.align_left_icon),
                        tint = if(isSystemInDarkTheme()) Color.White else Grey4
                    )
                }
                IconButton(
                    onClick = {
                        onAction(NotePageActions.OnAlignChange(NoteAlignment.JUSTIFY))
                    }
                ) {
                    Icon(
                        imageVector = AlignJustifyIcon,
                        contentDescription = stringResource(R.string.align_justify_icon),
                        tint = if(isSystemInDarkTheme()) Color.White else Grey4
                    )
                }
                IconButton(
                    onClick = {
                        onAction(NotePageActions.OnAlignChange(NoteAlignment.CENTER))
                    }
                ) {
                    Icon(
                        imageVector = AlignCenterIcon,
                        contentDescription = stringResource(R.string.align_center_icon),
                        tint = if(isSystemInDarkTheme()) Color.White else Grey4
                    )
                }
                IconButton(
                    onClick = {
                        onAction(NotePageActions.OnAlignChange(NoteAlignment.END))
                    }
                ) {
                    Icon(
                        imageVector = AlignRightIcon,
                        contentDescription = stringResource(R.string.align_end_icon),
                        tint = if(isSystemInDarkTheme()) Color.White else Grey4
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.color),
                style = MaterialTheme.typography.titleMedium,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                modifier = Modifier.weight(1f)
            )
            Row(
                modifier = Modifier
                    .weight(1f)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NoteColor.entries.forEach { noteColor ->
                    val color = noteColor.getColorForUiTheme()
                    Card(
                        modifier = Modifier.size(24.dp),
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = color
                        ),
                        onClick = { onAction(NotePageActions.OnColorChange(noteColor)) },
                        content = { }
                    )
                }
            }
        }
    }
}