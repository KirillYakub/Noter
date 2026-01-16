package com.kiras.noter.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val BlueLightTheme = Color(0xFFC2E9FB)
val GreenLightTheme = Color(0xFFC2FBC2)
val YellowLightTheme = Color(0xFFFFFEBD)
val OrangeLightTheme = Color(0xFFFFDEC8)
val PurpleLightTheme = Color(0xFFEABFFF)
val PinkLightTheme = Color(0xFFFFBDFD)

val BlueDarkTheme = Color(0xFF95DDFF)
val GreenDarkTheme = Color(0xFF98FF98)
val YellowDarkTheme = Color(0xFFFFFD9A)
val OrangeDarkTheme = Color(0xFFFFBD91)
val PurpleDarkTheme = Color(0xFFDA8FFF)
val PinkDarkTheme = Color(0xFFFF92FB)

val Grey1 = Color(0xFFE4E4E4)
val Grey2 = Color(0xFF323232)

val getBlueColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) BlueDarkTheme else BlueLightTheme

val getGreenColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) GreenDarkTheme else GreenLightTheme

val getYellowColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) YellowDarkTheme else YellowLightTheme

val getOrangeColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) OrangeDarkTheme else OrangeLightTheme

val getPurpleColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) PurpleDarkTheme else PurpleLightTheme

val getPinkColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) PinkDarkTheme else PinkLightTheme