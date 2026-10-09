package com.kiras.noter.presentation.note_page.mapper

import com.kiras.noter.domain.notes.model.folder.Folder
import com.kiras.noter.presentation.note_page.model.FolderSelectionUi

fun Folder.toFolderSelectionUi(isSelected: Boolean): FolderSelectionUi {
    return FolderSelectionUi(
        id = id,
        name = name,
        isSelected = isSelected
    )
}
