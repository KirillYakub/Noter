package com.kiras.noter.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoterBottomSheetScaffold(
    modifier: Modifier = Modifier,
    state: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    containerColor: Color,
    sheetContainerColor: Color,
    topAppBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
    bottomSheetContent: @Composable ColumnScope.() -> Unit
) {
    BottomSheetScaffold(
        modifier = modifier,
        topBar = topAppBar,
        scaffoldState = state,
        containerColor = containerColor,
        content = { padding ->
            Box(
                modifier = Modifier.fillMaxSize(),
                content = { content(padding) }
            )
        },
        sheetContainerColor = sheetContainerColor,
        sheetContent = bottomSheetContent
    )
}