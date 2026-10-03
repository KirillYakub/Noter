package com.kiras.noter.widgets.theme

import androidx.compose.ui.graphics.Color
import androidx.glance.color.ColorProvider
import com.kiras.noter.designsystem.Grey2
import com.kiras.noter.designsystem.Grey5
import com.kiras.noter.designsystem.MandarinDarkTheme

val WidgetContainerColor = ColorProvider(
    day = Color.White,
    night = Color.Black
)

val WidgetPrimaryTextColor = ColorProvider(
    day = Color.Black,
    night = Color.White
)

val WidgetSecondaryTextColor = ColorProvider(
    day = Grey5,
    night = Grey2
)

val WidgetAddButtonContainerColor = ColorProvider(
    day = Color.Black,
    night = MandarinDarkTheme
)
