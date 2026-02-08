package com.kiras.noter.presentation.accounts.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.kiras.noter.designsystem.BackIcon
import com.kiras.noter.designsystem.Grey5
import com.kiras.noter.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsStatusBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.accounts),
                style = MaterialTheme.typography.titleLarge,
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = BackIcon,
                    contentDescription = stringResource(com.kiras.noter.designsystem.R.string.go_back),
                    tint = if(isSystemInDarkTheme()) Color.White else Grey5
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier
    )
}