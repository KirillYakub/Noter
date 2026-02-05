package com.kiras.noter.presentation.settings.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kiras.noter.domain.notes.model.settings.NotesDisplayType
import com.kiras.noter.presentation.R

@Composable
fun NotesDisplayType.toText(): String {
    return when (this) {
        NotesDisplayType.LIST -> stringResource(R.string.list)
        NotesDisplayType.GRID -> stringResource(R.string.grid)
    }
}

@Composable
fun String.toNotesDisplayType(): NotesDisplayType {
    return when (this) {
        stringResource(R.string.grid) -> NotesDisplayType.GRID
        else -> NotesDisplayType.LIST
    }
}