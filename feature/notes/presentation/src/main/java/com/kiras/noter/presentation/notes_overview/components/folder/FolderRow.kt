package com.kiras.noter.presentation.notes_overview.components.folder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.AddIcon
import com.kiras.noter.designsystem.getFolderUnselectedItemBorderColor
import com.kiras.noter.designsystem.getFolderUnselectedItemColor
import com.kiras.noter.domain.notes.model.folder.Folder
import com.kiras.noter.domain.util.Constants.FOLDER_ALL_NAME
import com.kiras.noter.domain.util.Constants.FOLDER_IMPORTANT_NAME
import com.kiras.noter.presentation.R
import com.kiras.noter.presentation.components.FolderChip

@Composable
fun FolderRow(
    folders: List<Folder>,
    onFolderClick: (String) -> Unit,
    onFolderLongClick: (Folder) -> Unit,
    onCreateFolderClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedFolder: String = FOLDER_ALL_NAME,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 14.dp)
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FolderChip(
            text = stringResource(R.string.all),
            isSelected = selectedFolder == FOLDER_ALL_NAME,
            onClick = { onFolderClick(FOLDER_ALL_NAME) }
        )
        FolderChip(
            text = stringResource(R.string.important),
            isSelected = selectedFolder == FOLDER_IMPORTANT_NAME,
            onClick = { onFolderClick(FOLDER_IMPORTANT_NAME) }
        )
        folders.forEach { folder ->
            FolderChip(
                text = folder.name,
                isSelected = selectedFolder == folder.id,
                onClick = { onFolderClick(folder.id) },
                onLongClick = { onFolderLongClick(folder) }
            )
        }

        Surface(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(
                width = 1.dp,
                color = getFolderUnselectedItemBorderColor
            ),
            color = getFolderUnselectedItemColor,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .clickable(onClick = onCreateFolderClick)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = AddIcon,
                    contentDescription = stringResource(R.string.add_folder),
                    modifier = Modifier.size(12.dp),
                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
                Text(
                    text = stringResource(R.string.new_folder),
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        }
    }
}