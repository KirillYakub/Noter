package com.kiras.noter.presentation.registration.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.CheckIcon
import com.kiras.noter.designsystem.CloseIcon
import com.kiras.noter.designsystem.Grey5
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.getAuthCheckIconColor
import com.kiras.noter.designsystem.getAuthCloseIconColor

@Composable
fun PasswordRequirement(
    text: String,
    isValid: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if(isValid) CheckIcon else CloseIcon,
            contentDescription = null,
            tint = if(isValid) getAuthCheckIconColor else getAuthCloseIconColor
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            color = if(isSystemInDarkTheme()) Color.White else Grey5
        )
    }
}

@Preview
@Composable
fun PasswordRequirementPreview() {
    NoterTheme {
        PasswordRequirement(
            text = "Has number",
            isValid = true
        )
    }
}