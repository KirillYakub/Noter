package com.kiras.noter.presentation.notes_overview.components.folder

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.getFolderTextFieldsColor
import com.kiras.noter.designsystem.getFolderTextFieldsTextColor
import com.kiras.noter.presentation.R

@Composable
fun FolderEditTextField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        textStyle = MaterialTheme.typography.titleMedium,
        placeholder = {
            Text(
                text = stringResource(id = R.string.type_here),
                style = MaterialTheme.typography.titleMedium,
                color = getFolderTextFieldsTextColor.copy(alpha = 0.8f)
            )
        },
        label = {
            Text(
                text = stringResource(id = R.string.new_folder_field_label),
                style = MaterialTheme.typography.titleSmall,
                color = getFolderTextFieldsTextColor
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = getFolderTextFieldsColor,
            unfocusedContainerColor = getFolderTextFieldsColor.copy(alpha = 0.8f),
            focusedTextColor = getFolderTextFieldsTextColor,
            unfocusedTextColor = getFolderTextFieldsTextColor.copy(alpha = 0.8f),
        ),
        modifier = modifier
    )
}