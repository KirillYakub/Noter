package com.kiras.noter.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.EmailIcon
import com.kiras.noter.designsystem.Grey3
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.getAuthTextFieldsColor
import com.kiras.noter.designsystem.getAuthTextFieldsIconsColor
import com.kiras.noter.designsystem.getAuthTextFieldsTextColor

@Composable
fun AuthTextField(
    state: TextFieldState,
    hint: String,
    title: String?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if(title != null) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = if (isSystemInDarkTheme()) Color.White else Grey3
            )
        }
        TextField(
            state = state,
            textStyle = MaterialTheme.typography.titleMedium,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.titleMedium,
                    color = getAuthTextFieldsTextColor.copy(alpha = 0.8f)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = getAuthTextFieldsColor,
                unfocusedContainerColor = getAuthTextFieldsColor.copy(alpha = 0.8f),
                focusedTextColor = getAuthTextFieldsTextColor,
                unfocusedTextColor = getAuthTextFieldsTextColor.copy(alpha = 0.8f),
            ),
            leadingIcon = {
                Icon(
                    imageVector = EmailIcon,
                    contentDescription = null,
                    tint = getAuthTextFieldsIconsColor
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun RegistrationEmailTextFieldPreview() {
    NoterTheme {
        AuthTextField(
            state = rememberTextFieldState(),
            hint = "Email example",
            title = "Email",
            modifier = Modifier.fillMaxWidth(),
        )
    }
}