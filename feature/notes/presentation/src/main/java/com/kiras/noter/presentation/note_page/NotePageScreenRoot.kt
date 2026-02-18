package com.kiras.noter.presentation.note_page

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.anim.SlideFadeAnimatedContent
import com.kiras.noter.designsystem.components.NoterBottomSheetScaffold
import com.kiras.noter.designsystem.extentions.darken
import com.kiras.noter.designsystem.extentions.getTextColorForBackground
import com.kiras.noter.designsystem.getBottomSheetDefaultColor
import com.kiras.noter.designsystem.getDefaultColor
import com.kiras.noter.designsystem.getDefaultLineColor
import com.kiras.noter.domain.notes.model.NoteColor
import com.kiras.noter.domain.notes.model.settings.NotesStyleType
import com.kiras.noter.presentation.R
import com.kiras.noter.presentation.note_page.components.BottomSheetContent
import com.kiras.noter.presentation.note_page.components.NotePageStatusBar
import com.kiras.noter.presentation.note_page.components.NoteTextField
import com.kiras.noter.presentation.note_page.mapper.toTextAlign
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
    val isNoteColorDefault = state.noteUi.color == NoteColor.DEFAULT
    val isNoteStyleColorLime = state.noteStyleType == NotesStyleType.COLOR_LINE
    val useDefaultColor = isNoteColorDefault || isNoteStyleColorLime

    val contentColor =
        if(useDefaultColor && isSystemInDarkTheme()) Color.White else Color.Black

    val defaultBottomSheetColor = getBottomSheetDefaultColor
    val noteContainerColor =
        if(useDefaultColor) getDefaultColor else state.noteUi.color.getColorForUiTheme()

    val lineColor =
        if(isNoteColorDefault) getDefaultLineColor else state.noteUi.color.getColorForUiTheme()

    val bottomSheetColor = remember(
        useDefaultColor,
        noteContainerColor
    ) {
        if (useDefaultColor) defaultBottomSheetColor
        else noteContainerColor.darken()
    }
    val bottomSheetContentColor = bottomSheetColor.getTextColorForBackground()

    SlideFadeAnimatedContent(visible = state.showNoteContent) {
        NoterBottomSheetScaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = noteContainerColor,
            sheetContainerColor = bottomSheetColor,
            topAppBar = {
                NotePageStatusBar(
                    contentColor = contentColor.copy(alpha = 0.8f),
                    onBackClick = { onAction(NotePageActions.OnBackClick) },
                    onLikeClick = {},
                    onFolderClick = {},
                    onSendClick = {}
                )
            },
            content = { padding ->
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = padding.calculateTopPadding(),
                            bottom = padding.calculateBottomPadding(),
                        )
                        .padding(horizontal = 14.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        NoteTextField(
                            value = state.noteUi.title,
                            contentColor = contentColor,
                            textAlign = state.alignment.toTextAlign(),
                            onValueChange = { onAction(NotePageActions.OnTitleChange(it)) },
                            textStyle = MaterialTheme.typography.displayMedium,
                            placeholder = stringResource(R.string.write_title_here),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 14.dp),
                        )
                        if (state.noteUi.createTime.isNotBlank()) {
                            Text(
                                text = state.noteUi.createTime,
                                textAlign = state.alignment.toTextAlign(),
                                style = MaterialTheme.typography.labelSmall,
                                color = contentColor.copy(alpha = 0.8f),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .padding(top = 4.dp)
                            )
                        }
                        NoteTextField(
                            value = state.noteUi.content,
                            contentColor = contentColor,
                            textAlign = state.alignment.toTextAlign(),
                            onValueChange = { onAction(NotePageActions.OnContentChange(it)) },
                            textStyle = MaterialTheme.typography.labelMedium,
                            placeholder = stringResource(R.string.write_here),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 14.dp),
                        )
                    }
                    if (state.noteStyleType == NotesStyleType.COLOR_LINE) {
                        Box(
                            modifier = Modifier
                                .weight(0.025f)
                                .padding(top = 30.dp),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Spacer(
                                modifier = Modifier
                                    .size(
                                        width = 3.dp,
                                        height = 100.dp
                                    )
                                    .background(lineColor)
                                    .clip(CircleShape)
                            )
                        }
                    }
                }
            },
            bottomSheetContent = {
                BottomSheetContent(
                    contentColor = bottomSheetContentColor,
                    onAction = onAction
                )
            }
        )
    }
}

@Preview
@Composable
fun NotePageScreenPreview() {
    NoterTheme {
        NotePageScreen(
            state = NotePageState(
                noteStyleType = NotesStyleType.COLOR_LINE,
            ),
            onAction = {}
        )
    }
}
