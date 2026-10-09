package com.kiras.noter.presentation.note_page.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.kiras.noter.designsystem.BackIcon
import com.kiras.noter.presentation.R
import com.kiras.noter.presentation.components.FolderChip
import com.kiras.noter.presentation.note_page.model.FolderSelectionUi

@Composable
fun FolderSelectionDialog(
    folders: List<FolderSelectionUi>,
    onFolderToggle: (String, Boolean) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
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
                        text = stringResource(R.string.select_folders),
                        style = MaterialTheme.typography.titleLarge,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black
                    )
                }
                if (folders.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.no_custom_folders),
                            style = MaterialTheme.typography.bodyLarge,
                            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
                        )
                    }
                }
                else {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        folders.forEach { folder ->
                            FolderChip(
                                text = folder.name,
                                isSelected = folder.isSelected,
                                onClick = { onFolderToggle(folder.id, !folder.isSelected) }
                            )
                        }
                    }
                }
            }
        }
    }
}
