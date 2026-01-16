package com.kiras.noter.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.kiras.noter.designsystem.getBlueColor
import com.kiras.noter.designsystem.getGreenColor
import com.kiras.noter.designsystem.getOrangeColor
import com.kiras.noter.designsystem.getPinkColor
import com.kiras.noter.designsystem.getPurpleColor
import com.kiras.noter.designsystem.getYellowColor
import com.kiras.noter.domain.model.NoteColor

@Composable
fun NoteColor.getColorForUiTheme(): Color {
    return when(this) {
        NoteColor.BLUE -> getBlueColor
        NoteColor.GREEN -> getGreenColor
        NoteColor.YELLOW -> getYellowColor
        NoteColor.ORANGE -> getOrangeColor
        NoteColor.PURPLE -> getPurpleColor
        NoteColor.PINK -> getPinkColor
    }
}