package com.kiras.noter.presentation.settings.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.BackIcon
import com.kiras.noter.designsystem.Grey5
import com.kiras.noter.designsystem.PopUpIcon
import com.kiras.noter.designsystem.PopUpOpenIcon
import com.kiras.noter.domain.accounts.model.AccountIcon
import com.kiras.noter.presentation.R
import com.kiras.noter.ui.getAccountIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsStatusBar(
    icon: AccountIcon,
    modifier: Modifier = Modifier,
    dropdown: @Composable (expanded: Boolean, onDismiss: () -> Unit) -> Unit,
    onBackClick: () -> Unit
) {
    var showDropDown by remember { mutableStateOf(false) }

    Box {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.settings),
                    style = MaterialTheme.typography.titleLarge,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = BackIcon,
                        contentDescription = stringResource(com.kiras.noter.designsystem.R.string.go_back),
                        tint = if (isSystemInDarkTheme()) Color.White else Grey5
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ),
            actions = {
                Image(
                    imageVector = icon.getAccountIcon(),
                    contentDescription = stringResource(R.string.account_settings_icon),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable(
                            indication = null,
                            interactionSource = null,
                            onClick = { showDropDown = !showDropDown }
                        )
                )
            },
            modifier = modifier
        )
        dropdown(showDropDown) {
            showDropDown = false
        }
    }
}