package com.kiras.noter.presentation.notes_overview.components.folder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.kiras.noter.designsystem.BackIcon
import com.kiras.noter.designsystem.Grey1
import com.kiras.noter.designsystem.Grey5
import com.kiras.noter.designsystem.getActiveAuthButtonColor
import com.kiras.noter.designsystem.getNonActiveAuthButtonColor
import com.kiras.noter.domain.notes.model.folder.Folder
import com.kiras.noter.domain.notes.model.folder.FolderDialogMode
import com.kiras.noter.presentation.R

@Composable
fun FolderEditDialog(
    mode: FolderDialogMode,
    folder: Folder?,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onShowDeleteConfirmation: () -> Unit,
    onCancelDeleteConfirmation: () -> Unit
) {
    var folderName by remember { mutableStateOf(folder?.name ?: "") }

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
                    IconButton(
                        onClick = {
                            if (mode == FolderDialogMode.DELETE) onCancelDeleteConfirmation()
                            else onDismiss()
                        }
                    ) {
                        Icon(
                            imageVector = BackIcon,
                            contentDescription = stringResource(R.string.back),
                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                        )
                    }
                    Text(
                        text = when (mode) {
                            FolderDialogMode.CREATE -> stringResource(R.string.create_folder)
                            FolderDialogMode.RENAME -> stringResource(R.string.rename_folder)
                            FolderDialogMode.DELETE -> stringResource(R.string.delete)
                        },
                        style = MaterialTheme.typography.titleLarge,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black
                    )
                }
                when (mode) {
                    FolderDialogMode.CREATE, FolderDialogMode.RENAME -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            FolderEditTextField(
                                text = folderName,
                                onTextChange = { newFolderName -> folderName = newFolderName },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = { onSave(folderName.trim()) },
                                    enabled = folderName.isNotBlank(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = getActiveAuthButtonColor,
                                        contentColor = Color.White,
                                        disabledContainerColor = getNonActiveAuthButtonColor,
                                        disabledContentColor = if(isSystemInDarkTheme()) Grey1 else Grey5
                                    ),
                                    shape = CircleShape,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = if (mode == FolderDialogMode.CREATE) stringResource(R.string.create) else stringResource(R.string.save),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }

                                if (mode == FolderDialogMode.RENAME) {
                                    OutlinedButton(
                                        onClick = onShowDeleteConfirmation,
                                        modifier = Modifier.weight(1f),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Transparent,
                                            contentColor = MaterialTheme.colorScheme.error
                                        ),
                                        border = BorderStroke(
                                            width = 0.5.dp,
                                            color = MaterialTheme.colorScheme.error,
                                        ),
                                        shape = CircleShape,
                                    ) {
                                        Text(
                                            text = stringResource(R.string.delete),
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                }
                            }
                        }
                    }
                    FolderDialogMode.DELETE -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.delete_folder_warning, folder?.name ?: ""),
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Justify,
                                color = if (isSystemInDarkTheme()) Color.White else Color.Black
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = onDeleteClick,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.error,
                                        contentColor = Color.White
                                    ),
                                    shape = CircleShape,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        text = stringResource(R.string.delete),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                                OutlinedButton(
                                    onClick = onCancelDeleteConfirmation,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent,
                                        contentColor = if(isSystemInDarkTheme()) Grey1 else Grey5,
                                    ),
                                    border = BorderStroke(
                                        width = 0.5.dp,
                                        color = if(isSystemInDarkTheme()) Grey1 else Grey5,
                                    ),
                                    shape = CircleShape,
                                    modifier = Modifier.weight(1f),
                                ) {
                                    Text(
                                        text = stringResource(R.string.cancel),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}