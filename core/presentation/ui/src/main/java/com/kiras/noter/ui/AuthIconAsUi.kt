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
import com.kiras.noter.domain.accounts.model.AccountIcon

@Composable
fun AccountIcon.getAccountIcon(): ImageVector {
    return when(this) {
        AccountIcon.ICON_1 -> AuthIcon1
        AccountIcon.ICON_2 -> AuthIcon2
        AccountIcon.ICON_3 -> AuthIcon3
        AccountIcon.ICON_4 -> AuthIcon4
        AccountIcon.ICON_5 -> AuthIcon5
        AccountIcon.ICON_6 -> AuthIcon6
        AccountIcon.ICON_7 -> AuthIcon7
        AccountIcon.ICON_8 -> AuthIcon8
        AccountIcon.ICON_9 -> AuthIcon9
        AccountIcon.ICON_10 -> AuthIcon10
        AccountIcon.ICON_11 -> AuthIcon11
        AccountIcon.ICON_12 -> AuthIcon12
        AccountIcon.ICON_13 -> AuthIcon13
        AccountIcon.ICON_14 -> AuthIcon14
        AccountIcon.ICON_15 -> AuthIcon15
    }
}