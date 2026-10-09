package com.kiras.noter.presentation.notes_overview

import com.kiras.noter.domain.notes.model.folder.Folder

sealed interface NotesOverviewActions {
    data object OnMenuClick: NotesOverviewActions
    data object OnFullCalendarClick : NotesOverviewActions
    data object OnDismissFullCalendar : NotesOverviewActions
    data object OnAddNote: NotesOverviewActions
    data class OnNoteClick(val noteId: String): NotesOverviewActions
    data class OnDeleteNote(val noteId: String): NotesOverviewActions
    data class OnCopyNote(val noteId: String): NotesOverviewActions
    data class OnCalendarDaySelected(val dayId: String?) : NotesOverviewActions
    data class OnFolderSelected(val folderId: String) : NotesOverviewActions
    data object OnCreateFolderClick : NotesOverviewActions
    data class OnEditFolderClick(val folder: Folder) : NotesOverviewActions
    data class OnDeleteFolderClick(val folderId: String) : NotesOverviewActions
    data object OnDismissFolderDialog : NotesOverviewActions
    data class OnSaveFolder(val name: String) : NotesOverviewActions
    data object OnShowDeleteFolderConfirmation : NotesOverviewActions
    data object OnCancelDeleteConfirmation : NotesOverviewActions
}
