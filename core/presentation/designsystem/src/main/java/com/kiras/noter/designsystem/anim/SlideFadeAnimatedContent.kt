package com.kiras.noter.designsystem.anim

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SlideFadeAnimatedContent(
    visible: Boolean,
    modifier: Modifier = Modifier,
    durationMillis: Int = 200,
    easing: Easing = EaseInOut,
    content: @Composable () -> Unit
) {
    val screenWidthDp = LocalWindowInfo.current.containerDpSize.width
    val offset = remember {
        Animatable(screenWidthDp, Dp.VectorConverter)
    }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(visible) {
        if(visible) {
            alpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis, easing = easing)
            )
            offset.animateTo(
                targetValue = 0.dp,
                animationSpec = tween(durationMillis, easing = easing)
            )
        }
    }

    Box(
        modifier = modifier
            .offset(x = offset.value)
            .alpha(alpha.value)
    ) {
        content()
    }
}