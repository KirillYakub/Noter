package com.kiras.noter.notes.presentation.note_page

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.components.NoterBottomSheetScaffold
import com.kiras.noter.notes.presentation.note_page.components.NotePageStatusBar
import com.kiras.noter.domain.model.NoteColor
import com.kiras.noter.notes.R
import com.kiras.noter.notes.presentation.note_page.components.BottomSheetContent
import com.kiras.noter.notes.presentation.note_page.components.NoteTextField
import com.kiras.noter.notes.presentation.util.toTextAlign
import com.kiras.noter.ui.getColorForUiTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotePageScreenRoot(
    onBackClick: () -> Unit,
    model: NotePageViewModel = koinViewModel()
) {
    NotePageScreen(
        state = model.notePageState,
        onAction = { action ->
            when(action) {
                is NotePageActions.OnBackClick -> onBackClick()
                else -> model.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotePageScreen(
    state: NotePageState,
    onAction: (NotePageActions) -> Unit,
) {
    val isBackgroundColorDefault =
        state.noteUi.color == NoteColor.DEFAULT && isSystemInDarkTheme()

    NoterBottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = state.noteUi.color.getColorForUiTheme(),
        topAppBar = {
            NotePageStatusBar(
                isBackgroundColorDefault = isBackgroundColorDefault,
                onBackClick = { onAction(NotePageActions.OnBackClick) },
                onLikeClick = {},
                onFolderClick = {},
                onSendClick = {}
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = padding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding(),
                    )
                    .padding(horizontal = 14.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                NoteTextField(
                    value = state.noteUi.title,
                    isBackgroundColorDefault = isBackgroundColorDefault,
                    textAlign = state.alignment.toTextAlign(),
                    onValueChange = { onAction(NotePageActions.OnTitleChange(it)) },
                    textStyle = MaterialTheme.typography.displayMedium,
                    placeholder = stringResource(R.string.write_title_here),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp),
                )
                if(state.noteUi.createTime.isNotBlank()) {
                    Text(
                        text = state.noteUi.createTime,
                        textAlign = state.alignment.toTextAlign(),
                        style = MaterialTheme.typography.labelSmall,
                        color = (if (isBackgroundColorDefault) Color.White else Color.Black).copy(alpha = 0.8f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 4.dp)
                    )
                }
                NoteTextField(
                    value = state.noteUi.content,
                    isBackgroundColorDefault = isBackgroundColorDefault,
                    textAlign = state.alignment.toTextAlign(),
                    onValueChange = { onAction(NotePageActions.OnContentChange(it)) },
                    textStyle = MaterialTheme.typography.labelMedium,
                    placeholder = stringResource(R.string.write_here),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp),
                )
            }
        },
        bottomSheetContent = {
            BottomSheetContent(
                onAction = onAction
            )
        }
    )
}

@Preview
@Composable
fun NotePageScreenPreview() {
    NoterTheme {
        NotePageScreen(
            state = NotePageState(),
            onAction = {}
        )
    }
}
