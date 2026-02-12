package com.kiras.noter.designsystem.extentions

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils
import com.kiras.noter.designsystem.Grey5

fun Color.darken(factor: Float = 0.85f): Color {
    val hsl = FloatArray(3)
    ColorUtils.colorToHSL(toArgb(), hsl)
    hsl[2] = (hsl[2] * factor).coerceIn(0f, 1f)
    return Color(ColorUtils.HSLToColor(hsl))
}

fun Color.getTextColorForBackground(): Color {
    return if (luminance() > 0.5f) {
        Grey5
    } else {
        Color.White
    }
}
