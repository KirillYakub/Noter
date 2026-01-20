package com.kiras.noter.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.PinkLightTheme

@Composable
fun NoteOverviewItem(
    title: String,
    content: String,
    color: Color,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if(title.isNotBlank()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if(content.isNotBlank()) {
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun NoteOverviewItemPreview() {
    NoterTheme {
        NoteOverviewItem(
            title = "Lorem ipsum",
            content = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad",
            color = PinkLightTheme,
            onClick = {}
        )
    }
}