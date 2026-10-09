package com.kiras.noter.presentation.notes_overview

import androidx.compose.foundation.text.input.TextFieldState
import com.kiras.noter.domain.notes.model.folder.Folder
import com.kiras.noter.domain.notes.model.settings.NotesDisplayType
import com.kiras.noter.domain.notes.model.settings.NotesSortType
import com.kiras.noter.domain.notes.model.settings.NotesStyleType
import com.kiras.noter.model.NoteUi
import com.kiras.noter.presentation.notes_overview.model.CalendarDayUi
import com.kiras.noter.domain.notes.model.folder.FolderDialogMode
import com.kiras.noter.domain.util.Constants.FOLDER_ALL_NAME

data class NotesOverviewState(
    val searchQuery: TextFieldState = TextFieldState(),
    val notesStyle: NotesStyleType = NotesStyleType.COLOR_FULL,
    val notesSortType: NotesSortType = NotesSortType.DATE,
    val notesDisplayType: NotesDisplayType = NotesDisplayType.GRID,
    val isCalendarDaysVisible: Boolean = false,
    val isFullCalendarVisible: Boolean = false,
    val calendarDays: List<CalendarDayUi> = emptyList(),
    val selectedDayId: String? = null,
    val folders: List<Folder> = emptyList(),
    val selectedFolder: String = FOLDER_ALL_NAME,
    val isFolderDialogOpen: Boolean = false,
    val folderDialogMode: FolderDialogMode = FolderDialogMode.CREATE,
    val folderToEdit: Folder? = null,
    val notes: List<NoteUi> = emptyList(),
    val isLoading: Boolean = false,
)
