package com.kiras.noter.presentation.settings.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.Grey1
import com.kiras.noter.designsystem.Grey5
import com.kiras.noter.designsystem.PopUpIcon
import com.kiras.noter.designsystem.PopUpOpenIcon
import com.kiras.noter.domain.notes.model.settings.NotesDisplayType
import com.kiras.noter.domain.notes.model.settings.NotesSortType
import com.kiras.noter.domain.notes.model.settings.NotesStyleType
import com.kiras.noter.presentation.R
import com.kiras.noter.presentation.settings.SettingsActions
import com.kiras.noter.presentation.settings.mapper.toText

@Composable
fun SettingsDropDown(
    text: String,
    dropdown: @Composable (expanded: Boolean, onDismiss: () -> Unit) -> Unit
) {
    var showDropDown by remember { mutableStateOf(false) }
    Box {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
            IconButton(onClick = {
                showDropDown = !showDropDown
            }) {
                Icon(
                    imageVector = if (showDropDown) PopUpOpenIcon else PopUpIcon,
                    contentDescription = stringResource(R.string.open_settings_dialog),
                    tint = if (isSystemInDarkTheme()) Color.White else Grey5
                )
            }
        }
        dropdown(showDropDown) {
            showDropDown = false
        }
    }
}

@Composable
fun DisplayNotesDisplayStyleDropDownMenu(
    selected: NotesDisplayType,
    expanded: Boolean,
    onDismiss: () -> Unit,
    onAction: (SettingsActions) -> Unit
) {
    DropdownMenuContainer(
        expanded = expanded,
        onDismiss = onDismiss
    ) {
        NotesDisplayType.entries.forEach { type ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = type.toText(),
                        style = MaterialTheme.typography.titleSmall,
                        color = (if(isSystemInDarkTheme()) Color.White else Color.Black).copy(
                            alpha = if(type == selected) 1f else 0.6f
                        )
                    )
                },
                onClick = {
                    onAction(SettingsActions.OnNotesDisplayChange(type))
                    onDismiss()
                },
            )
        }
    }
}

@Composable
fun DisplayNotesSortDropDownMenu(
    selected: NotesSortType,
    expanded: Boolean,
    onDismiss: () -> Unit,
    onAction: (SettingsActions) -> Unit
) {
    DropdownMenuContainer(
        expanded = expanded,
        onDismiss = onDismiss
    ) {
        NotesSortType.entries.forEach { type ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = type.toText(),
                        style = MaterialTheme.typography.titleSmall,
                        color = (if(isSystemInDarkTheme()) Color.White else Color.Black).copy(
                            alpha = if(type == selected) 1f else 0.6f
                        )
                    )
                },
                onClick = {
                    onAction(SettingsActions.OnNotesSortChange(type))
                    onDismiss()
                }
            )
        }
    }
}

@Composable
fun DisplayAccountDropDownMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    onAction: (SettingsActions) -> Unit
) {
    DropdownMenuContainer(
        expanded = expanded,
        onDismiss = onDismiss
    ) {
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(R.string.logout),
                    style = MaterialTheme.typography.titleSmall,
                    color = if(isSystemInDarkTheme()) Color.White else Color.Black
                )
            },
            onClick = {
                onAction(SettingsActions.OnLogoutClick)
                onDismiss()
            }
        )
    }
}

@Composable
private fun DropdownMenuContainer(
    expanded: Boolean,
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    val screenWidthDp = LocalWindowInfo.current.containerDpSize.width

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        containerColor = if(isSystemInDarkTheme()) Grey5 else Grey1,
        offset = remember { DpOffset(screenWidthDp, 0.dp) },
        content = content
    )
}