package com.kiras.noter.presentation.settings.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kiras.noter.designsystem.getSettingsSwitcherColor
import com.kiras.noter.designsystem.getSettingsSwitcherNotActiveColor

@Composable
fun SettingsSwitcher(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedTrackColor = getSettingsSwitcherColor,
                checkedThumbColor = Color.White,
                uncheckedTrackColor = getSettingsSwitcherNotActiveColor,
                uncheckedThumbColor = Color.White,
                checkedBorderColor = getSettingsSwitcherColor,
                uncheckedBorderColor = getSettingsSwitcherNotActiveColor
            )
        )
    }
}