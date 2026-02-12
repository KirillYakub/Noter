package com.kiras.noter.presentation.settings.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.Grey4
import com.kiras.noter.designsystem.Grey5
import com.kiras.noter.designsystem.getPurpleColor
import com.kiras.noter.domain.notes.model.settings.NotesStyleType
import com.kiras.noter.presentation.R

@Composable
fun SettingsNoteStyle(
    selected: NotesStyleType,
    modifier: Modifier = Modifier,
    onStyleChange: (NotesStyleType) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = stringResource(R.string.notes_style),
            style = MaterialTheme.typography.titleMedium,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .alpha(if(selected == NotesStyleType.COLOR_FULL) 1f else 0.6f)
            ) {
                Text(
                    text = stringResource(id = R.string.full_color),
                    style = MaterialTheme.typography.titleSmall,
                    color = if (isSystemInDarkTheme()) Color.White else Grey4
                )
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = getPurpleColor
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { onStyleChange(NotesStyleType.COLOR_FULL) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.your_text),
                        style = MaterialTheme.typography.titleSmall,
                        color = if(isSystemInDarkTheme()) Grey5 else Color.Black,
                        modifier = Modifier.padding(10.dp),
                    )
                }
            }

            val density = LocalDensity.current
            var colorLineHeightDp by remember { mutableStateOf(0.dp) }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .alpha(if(selected == NotesStyleType.COLOR_LINE) 1f else 0.6f)
            ) {
                Text(
                    text = stringResource(id = R.string.color_line),
                    style = MaterialTheme.typography.titleSmall,
                    color = if (isSystemInDarkTheme()) Color.White else Grey4
                )
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = if(isSystemInDarkTheme()) Color.Black else Color.White
                    ),
                    border = BorderStroke(1.dp, if(isSystemInDarkTheme()) Color.White else Color.Black),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { onStyleChange(NotesStyleType.COLOR_LINE) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.your_text),
                            style = MaterialTheme.typography.titleSmall,
                            color = if(isSystemInDarkTheme()) Grey5 else Color.Black,
                            modifier = Modifier.onGloballyPositioned {
                                colorLineHeightDp = with(density) { it.size.height.toDp() }
                            }
                        )
                        Spacer(
                            modifier = Modifier
                                .size(
                                    width = 3.dp,
                                    height = colorLineHeightDp
                                )
                                .background(getPurpleColor)
                                .clip(CircleShape)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingNoteStylePreview() {
    SettingsNoteStyle(selected = NotesStyleType.COLOR_FULL) { }
}