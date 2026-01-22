package com.kiras.noter.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kiras.noter.designsystem.getBottomSheetColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoterBottomSheetScaffold(
    modifier: Modifier = Modifier,
    containerColor: Color,
    topAppBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
    bottomSheetContent: @Composable ColumnScope.() -> Unit
) {
    BottomSheetScaffold(
        modifier = modifier,
        topBar = topAppBar,
        containerColor = containerColor,
        content = { padding ->
            Box(
                modifier = Modifier.fillMaxSize(),
                content = { content(padding) }
            )
        },
        sheetContainerColor = getBottomSheetColor,
        sheetContent = bottomSheetContent
    )
}