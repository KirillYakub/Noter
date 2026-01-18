package com.kiras.noter.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun NoterTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = typography,
        content = content
    )
}