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
val Grey2 = Color(0xFFDEDEDE)
val Grey3 = Color(0xFFB5B5B5)
val Grey4 = Color(0xFF515151)
val Grey5 = Color(0xFF323232)

val MandarinLightTheme = Color(0xFFFFAD33)
val MandarinDarkTheme = Color(0xFFFF9900)

val LimeLightTheme = Color(0xFF41FF6A)
val LimeDarkTheme = Color(0xFF00FF37)

val RedLightTheme = Color(0xFFFF6969)
val RedDarkTheme = Color(0xFFFF0000)

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

val getDefaultColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color.Black else Color.White

val getDefaultLineColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color.White else Color.Black

val getCalendarSelectedItemColor: Color
    get() = Grey5

val getCalendarUnselectedItemColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color.Black else Color.White

val getCalenderSelectedItemBorderColor: Color
    get() = Color.Transparent

val getCalendarUnselectedItemBorderColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Color.White else Color.Black

val getSearchBarColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Grey5 else Grey1

val getBottomSheetDefaultColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Grey5 else Grey1

val getNoteAddButtonColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) MandarinDarkTheme else Color.Black

val getAuthTextFieldsTextColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Grey2 else Grey5

val getAuthTextFieldsIconsColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Grey2 else Grey4

val getAuthTextFieldsColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Grey5 else Grey1

val getAuthCheckIconColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) LimeDarkTheme else LimeLightTheme

val getAuthCloseIconColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) RedDarkTheme else RedLightTheme

val getLoginButtonColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) GreenDarkTheme else GreenDarkTheme

val getActiveAuthButtonColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) MandarinDarkTheme else MandarinLightTheme

val getNonActiveAuthButtonColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Grey4 else Grey2

val getSettingsContainerColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Grey5 else Grey1

val getSettingsSwitcherColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) MandarinDarkTheme else MandarinLightTheme

val getSettingsSwitcherNotActiveColor: Color
    @Composable
    get() = if(isSystemInDarkTheme()) Grey4 else Grey3