package com.kiras.noter.designsystem.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.kiras.noter.designsystem.Grey1
import com.kiras.noter.designsystem.Grey2
import com.kiras.noter.designsystem.Grey4
import com.kiras.noter.designsystem.R

@Composable
fun EmptyListLabel(
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.it_s_empty_here),
    color: Color = if(isSystemInDarkTheme()) Grey2 else Grey4
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = color,
            modifier = modifier
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
    }
}