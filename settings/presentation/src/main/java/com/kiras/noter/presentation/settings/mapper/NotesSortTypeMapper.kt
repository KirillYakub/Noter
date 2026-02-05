package com.kiras.noter.presentation.settings.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kiras.noter.domain.notes.model.settings.NotesSortType
import com.kiras.noter.presentation.R

@Composable
fun NotesSortType.toText(): String {
    return when (this) {
        NotesSortType.DATE -> stringResource(R.string.creation_date)
        NotesSortType.ALPHABETICALLY -> stringResource(R.string.alphabet)
    }
}

@Composable
fun String.toNotesSortType(): NotesSortType {
    return when (this) {
        stringResource(R.string.creation_date) -> NotesSortType.DATE
        else -> NotesSortType.ALPHABETICALLY
    }
}