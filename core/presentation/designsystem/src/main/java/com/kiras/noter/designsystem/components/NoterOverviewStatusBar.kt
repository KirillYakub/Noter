package com.kiras.noter.designsystem.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.MenuIcon
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.SearchIcon
import com.kiras.noter.designsystem.getSearchBarColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoterOverviewStatusBar(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    hint: String,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    onMenuClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    state = state,
                    textStyle = MaterialTheme.typography.titleMedium.copy(
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black
                    ),
                    placeholder = {
                        Text(
                            text = hint,
                            style = MaterialTheme.typography.titleMedium,
                            color = (if (isSystemInDarkTheme()) Color.White else Color.Black).copy(
                                alpha = 0.6f
                            )
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    lineLimits = TextFieldLineLimits.SingleLine,
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = getSearchBarColor,
                        unfocusedContainerColor = getSearchBarColor
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = SearchIcon,
                            contentDescription = null,
                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                        )
                    },
                    modifier = Modifier.weight(1f),
                )
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = MenuIcon,
                        contentDescription = null,
                        tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NotesOverviewStatusBarPreview() {
    NoterTheme {
        NoterOverviewStatusBar(
            state = TextFieldState(),
            hint = "Search",
            onMenuClick = {}
        )
    }
}