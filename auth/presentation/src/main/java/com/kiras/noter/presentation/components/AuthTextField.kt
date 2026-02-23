package com.kiras.noter.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.Grey4
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.getAuthTextFieldsColor
import com.kiras.noter.designsystem.getAuthTextFieldsIconsColor
import com.kiras.noter.designsystem.getAuthTextFieldsTextColor
import com.kiras.noter.presentation.R

@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    icon: ImageVector? = null,
    hint: String,
    maxLength: Int? = null,
    titleContent: @Composable (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        titleContent?.invoke()
        TextField(
            state = state,
            inputTransformation = maxLength?.let { InputTransformation.maxLength(it) },
            textStyle = MaterialTheme.typography.titleMedium,
            placeholder = {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.titleMedium,
                    color = getAuthTextFieldsTextColor.copy(alpha = 0.8f)
                )
            },
            lineLimits = TextFieldLineLimits.SingleLine,
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
                icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = getAuthTextFieldsIconsColor
                    )
                }
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
            titleContent = {
                Text(
                    text = stringResource(id = R.string.email),
                    style = MaterialTheme.typography.titleSmall,
                    color = if (isSystemInDarkTheme()) Color.White else Grey4
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}