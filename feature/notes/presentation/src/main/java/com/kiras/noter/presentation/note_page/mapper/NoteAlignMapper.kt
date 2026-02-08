package com.kiras.noter.presentation.note_page.mapper

import androidx.compose.ui.text.style.TextAlign
import com.kiras.noter.presentation.note_page.model.NoteAlignment

fun NoteAlignment.toTextAlign(): TextAlign {
    return when (this) {
        NoteAlignment.START -> TextAlign.Start
        NoteAlignment.END -> TextAlign.End
        NoteAlignment.CENTER -> TextAlign.Center
        NoteAlignment.JUSTIFY -> TextAlign.Justify
    }
}