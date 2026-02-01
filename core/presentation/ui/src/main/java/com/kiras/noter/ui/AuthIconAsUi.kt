package com.kiras.noter.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.kiras.noter.designsystem.AuthIcon1
import com.kiras.noter.designsystem.AuthIcon10
import com.kiras.noter.designsystem.AuthIcon11
import com.kiras.noter.designsystem.AuthIcon12
import com.kiras.noter.designsystem.AuthIcon13
import com.kiras.noter.designsystem.AuthIcon14
import com.kiras.noter.designsystem.AuthIcon15
import com.kiras.noter.designsystem.AuthIcon2
import com.kiras.noter.designsystem.AuthIcon3
import com.kiras.noter.designsystem.AuthIcon4
import com.kiras.noter.designsystem.AuthIcon5
import com.kiras.noter.designsystem.AuthIcon6
import com.kiras.noter.designsystem.AuthIcon7
import com.kiras.noter.designsystem.AuthIcon8
import com.kiras.noter.designsystem.AuthIcon9
import com.kiras.noter.domain.accounts.model.AuthIcon

@Composable
fun AuthIcon.getAuthIcon(): ImageVector {
    return when(this) {
        AuthIcon.ICON_1 -> AuthIcon1
        AuthIcon.ICON_2 -> AuthIcon2
        AuthIcon.ICON_3 -> AuthIcon3
        AuthIcon.ICON_4 -> AuthIcon4
        AuthIcon.ICON_5 -> AuthIcon5
        AuthIcon.ICON_6 -> AuthIcon6
        AuthIcon.ICON_7 -> AuthIcon7
        AuthIcon.ICON_8 -> AuthIcon8
        AuthIcon.ICON_9 -> AuthIcon9
        AuthIcon.ICON_10 -> AuthIcon10
        AuthIcon.ICON_11 -> AuthIcon11
        AuthIcon.ICON_12 -> AuthIcon12
        AuthIcon.ICON_13 -> AuthIcon13
        AuthIcon.ICON_14 -> AuthIcon14
        AuthIcon.ICON_15 -> AuthIcon15
    }
}