package com.kiras.noter.presentation.note_page.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kiras.noter.designsystem.BackIcon
import com.kiras.noter.designsystem.FolderIcon
import com.kiras.noter.designsystem.Grey5
import com.kiras.noter.designsystem.LikeIcon
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.R
import com.kiras.noter.designsystem.SendIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotePageStatusBar(
    modifier: Modifier = Modifier,
    contentColor: Color,
    isImportant: Boolean,
    onBackClick: () -> Unit,
    onLikeClick: () -> Unit,
    onFolderClick: () -> Unit,
    onSendClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = { },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = BackIcon,
                    contentDescription = stringResource(R.string.go_back),
                    tint = contentColor
                )
            }
        },
        actions = {
            IconButton(onClick = onLikeClick) {
                Icon(
                    imageVector = LikeIcon,
                    contentDescription = stringResource(R.string.like_note),
                    tint = if (isImportant) Color.Red else contentColor
                )
            }
            IconButton(onClick = onFolderClick) {
                Icon(
                    imageVector = FolderIcon,
                    contentDescription = stringResource(R.string.add_note_to_folder),
                    tint = contentColor
                )
            }
            IconButton(onClick = onSendClick) {
                Icon(
                    imageVector = SendIcon,
                    contentDescription = stringResource(R.string.send_note),
                    tint = contentColor
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun NotePageStatusBarPreview() {
    NoterTheme {
        NotePageStatusBar(
            modifier = Modifier.fillMaxWidth(),
            contentColor = Color.White,
            isImportant = false,
            onBackClick = {},
            onLikeClick = {},
            onFolderClick = {},
            onSendClick = {}
        )
    }
}
