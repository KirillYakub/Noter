package com.kiras.noter.presentation.note_page.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kiras.noter.designsystem.NoterTheme

@Composable
fun NoteTextField(
    modifier: Modifier = Modifier,
    contentColor: Color,
    textAlign: TextAlign,
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle,
    placeholder: String = ""
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle.copy(
            textAlign = textAlign
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.colors(
            cursorColor = contentColor.copy(alpha = 0.6f),
            focusedTextColor = contentColor,
            unfocusedTextColor = contentColor,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        placeholder = {
            if(placeholder.isNotBlank()) {
                Text(
                    text = placeholder,
                    textAlign = textAlign,
                    style = textStyle,
                    color = contentColor.copy(alpha = 0.6f),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun NoteTextFieldPreview() {
    NoterTheme {
        NoteTextField(
            value = "",
            contentColor = Color.White,
            onValueChange = {},
            textStyle = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start,
            placeholder = "Write here"
        )
    }
}