package com.kiras.noter.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.getFolderSelectedItemBorderColor
import com.kiras.noter.designsystem.getFolderSelectedItemColor
import com.kiras.noter.designsystem.getFolderUnselectedItemBorderColor
import com.kiras.noter.designsystem.getFolderUnselectedItemColor

@Composable
fun FolderChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) getFolderSelectedItemBorderColor else getFolderUnselectedItemBorderColor
        ),
        color = if (isSelected) getFolderSelectedItemColor else getFolderUnselectedItemColor,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick ?: {}
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = if (isSelected || isSystemInDarkTheme()) Color.White else Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}