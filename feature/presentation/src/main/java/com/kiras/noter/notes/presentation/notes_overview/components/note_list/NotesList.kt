package com.kiras.noter.notes.presentation.notes_overview.components.note_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kiras.noter.domain.model.NoteColor
import com.kiras.noter.notes.presentation.notes_overview.model.NoteDisplay
import com.kiras.noter.notes.model.NoteUi
import com.kiras.noter.ui.getColorForUiTheme

@Composable
fun NotesList(
    notes: List<NoteUi>,
    noteDisplay: NoteDisplay,
    onNoteClick: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        columns = GridCells.Fixed(if(noteDisplay == NoteDisplay.LIST) 1 else 2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = notes, key = { it.id!! }) { note ->
            NoteOverviewItem(
                content = note.content,
                title = note.title,
                color = note.color.getColorForUiTheme(),
                isColorDefault = note.color == NoteColor.DEFAULT,
                onClick = { onNoteClick(note.id!!) }
            )
        }
    }
}