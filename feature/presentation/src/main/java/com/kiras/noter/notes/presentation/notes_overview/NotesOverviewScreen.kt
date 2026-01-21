package com.kiras.noter.notes.presentation.notes_overview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.notes.presentation.notes_overview.components.NoteAddButton
import com.kiras.noter.designsystem.components.NoterScaffold
import com.kiras.noter.notes.presentation.notes_overview.components.NoterOverviewStatusBar
import com.kiras.noter.domain.model.NoteColor
import com.kiras.noter.notes.R
import com.kiras.noter.notes.presentation.notes_overview.components.calendar.CalendarRow
import com.kiras.noter.notes.presentation.notes_overview.components.note_list.NotesList
import com.kiras.noter.notes.presentation.notes_overview.model.CalendarDayUi
import com.kiras.noter.notes.presentation.notes_overview.model.NoteDisplay
import com.kiras.noter.notes.model.NoteUi
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NotesOverviewScreenRoot(
    onNoteClick: (String) -> Unit,
    onAddNoteClick: () -> Unit,
    viewModel: NotesOverviewViewModel = koinViewModel(),
) {
    NotesOverviewScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is NotesOverviewActions.OnNoteClick -> onNoteClick(action.noteId)
                NotesOverviewActions.OnAddNote -> onAddNoteClick()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotesOverviewScreen(
    state: NotesOverviewState,
    onAction: (NotesOverviewActions) -> Unit,
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = topAppBarState
    )
    NoterScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = if(isSystemInDarkTheme()) Color.Black else Color.White,
        topAppBar = {
            NoterOverviewStatusBar(
                modifier = Modifier.fillMaxWidth(),
                state = state.searchQuery,
                hint = stringResource(id = R.string.search),
                scrollBehavior = scrollBehavior,
                onMenuClick = {}
            )
        },
        floatingActionButton = {
            NoteAddButton(
                onClick = { onAction(NotesOverviewActions.OnAddNote) }
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
            ) {
                CalendarRow(
                    days = state.calendarDays,
                    onDayClick = { id ->
                        onAction(NotesOverviewActions.OnCalendarDaySelected(id))
                    }
                )
                NotesList(
                    notes = state.notes,
                    noteDisplay = state.noteDisplay,
                    onNoteClick = { id -> onAction(NotesOverviewActions.OnNoteClick(id)) }
                )
            }
        }
    )
}

@Preview
@Composable
fun NotesOverviewScreenPreview() {
    NoterTheme {
        NotesOverviewScreen(
            state = NotesOverviewState(
                noteDisplay = NoteDisplay.GRID,
                calendarDays = listOf(
                    CalendarDayUi(
                        id = "2024-02-01",
                        dayOfWeek = "Thu",
                        dayOfMonth = "1",
                        month = "Feb",
                        isSelected = false
                    ),
                    CalendarDayUi(
                        id = "2024-02-02",
                        dayOfWeek = "Fri",
                        dayOfMonth = "2",
                        month = "Feb",
                        isSelected = false
                    ),
                    CalendarDayUi(
                        id = "2024-02-03",
                        dayOfWeek = "Sat",
                        dayOfMonth = "3",
                        month = "Feb",
                        isSelected = true
                    ),
                    CalendarDayUi(
                        id = "2024-02-04",
                        dayOfWeek = "Sun",
                        dayOfMonth = "4",
                        month = "Feb",
                        isSelected = false
                    ),
                    CalendarDayUi(
                        id = "2024-02-05",
                        dayOfWeek = "Mon",
                        dayOfMonth = "5",
                        month = "Feb",
                        isSelected = false
                    )
                ),
                notes = listOf(
                    NoteUi(
                        id = "1",
                        title = "Buy groceries",
                        content = "Milk, eggs, bread, and vegetables",
                        color = NoteColor.YELLOW,
                        createTime = "2024-02-01 09:15",
                    ),
                    NoteUi(
                        id = "2",
                        title = "Workout plan",
                        content = "Chest and triceps workout at the gym",
                        color = NoteColor.GREEN,
                        createTime = "2024-02-02 18:00",
                    ),
                    NoteUi(
                        id = "3",
                        title = "Project ideas",
                        content = "Notes app with offline-first sync and calendar filter",
                        color = NoteColor.BLUE,
                        createTime = "2024-02-03 14:10",
                    ),
                    NoteUi(
                        id = "4",
                        title = "Meeting notes",
                        content = "Discuss architecture, modules, and DI setup",
                        color = NoteColor.PURPLE,
                        createTime = "2024-02-04 11:00",
                    ),
                    NoteUi(
                        id = "5",
                        title = "Travel checklist",
                        content = "Passport, tickets, charger, headphones",
                        color = NoteColor.DEFAULT,
                        createTime = "2024-02-05 20:05",
                    )
                )
            ),
            onAction = {}
        )
    }
}