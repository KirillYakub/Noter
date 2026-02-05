package com.kiras.noter.notes.presentation.notes_overview.components.note_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kiras.noter.domain.notes.model.NoteColor
import com.kiras.noter.domain.notes.model.settings.NotesDisplayType
import com.kiras.noter.notes.model.NoteUi
import com.kiras.noter.ui.getColorForUiTheme

@Composable
fun NotesList(
    notes: List<NoteUi>,
    notesDisplayType: NotesDisplayType,
    onNoteClick: (String) -> Unit,
    onNoteLongClick: (String) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        columns = StaggeredGridCells.Fixed(if(notesDisplayType == NotesDisplayType.LIST) 1 else 2),
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = notes, key = { it.id }) { note ->
            NoteOverviewItem(
                content = note.content,
                title = note.title,
                color = note.color.getColorForUiTheme(),
                isColorDefault = note.color == NoteColor.DEFAULT,
                onClick = { onNoteClick(note.id) },
                onDeleteClick = { onNoteLongClick(note.id) }
            )
        }
    }
}