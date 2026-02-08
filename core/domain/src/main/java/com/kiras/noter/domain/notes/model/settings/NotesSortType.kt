package com.kiras.noter.domain.notes.model.settings

import com.kiras.noter.domain.notes.model.Note

enum class NotesSortType {
    DATE,
    ALPHABETICALLY
}

fun NotesSortType.toComparator(): Comparator<Note> = when (this) {
    NotesSortType.DATE -> compareByDescending { it.createTime }
    NotesSortType.ALPHABETICALLY ->
        compareBy(String.CASE_INSENSITIVE_ORDER) { it.title + it.content }
}