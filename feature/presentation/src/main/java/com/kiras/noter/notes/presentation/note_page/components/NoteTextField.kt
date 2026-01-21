package com.kiras.noter.notes.presentation.note_page.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.tooling.preview.Preview
import com.kiras.noter.designsystem.NoterTheme

@Composable
fun NoteTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle,
    placeholder: String = ""
) {
    val contentColors = if(isSystemInDarkTheme()) Color.White else Color.Black

    TextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = contentColors,
            unfocusedTextColor = contentColors,
            cursorColor = contentColors,
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
                    style = textStyle,
                    color = contentColors.copy(alpha = 0.6f)
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
            onValueChange = {},
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = "Write here"
        )
    }
}