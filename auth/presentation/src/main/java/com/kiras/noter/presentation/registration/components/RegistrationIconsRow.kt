package com.kiras.noter.presentation.registration.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.Grey3
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.domain.model.AuthIcon
import com.kiras.noter.presentation.registration.RegisterAction
import com.kiras.noter.ui.getAuthIcon
import kotlinx.coroutines.delay
import kotlin.math.abs
import com.kiras.noter.presentation.R

@Composable
fun RegistrationIconsRow(
    modifier: Modifier = Modifier,
    authIcon: AuthIcon,
    onSelected: (AuthIcon) -> Unit
) {

    val pagerState = rememberPagerState(
        initialPage = authIcon.ordinal,
        pageCount = { AuthIcon.entries.size }
    )

    LaunchedEffect(pagerState.currentPage) {
        delay(300)
        onSelected(AuthIcon.entries[pagerState.currentPage])
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(id = R.string.icon),
            style = MaterialTheme.typography.titleSmall,
            color = if(isSystemInDarkTheme()) Color.White else Grey3
        )
        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val pageMaxSize = 80.dp
            val sidePadding = ((maxWidth - pageMaxSize) / 2).coerceAtLeast(0.dp)

            HorizontalPager(
                state = pagerState,
                pageSize = PageSize.Fixed(pageSize = pageMaxSize),
                contentPadding = PaddingValues(horizontal = sidePadding),
                flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) { page ->

                val offset = pagerState.getOffsetDistanceInPages(page)
                val dist = abs(offset)

                val (size: Dp, alpha: Float) = when {
                    dist <= 1f -> lerpDp(80.dp, 60.dp, dist) to 1f
                    dist <= 2f -> lerpDp(60.dp, 40.dp, dist - 1f) to 0.8f
                    else -> 40.dp to 0.6f
                }

                Box(
                    modifier = Modifier.size(pageMaxSize),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = AuthIcon.entries[page].getAuthIcon(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(size)
                            .clip(CircleShape)
                            .alpha(alpha)
                    )
                }
            }
        }
    }
}

private fun lerpDp(start: Dp, end: Dp, fraction: Float): Dp {
    val clampedFraction = fraction.coerceIn(0f, 1f)
    return start + (end - start) * clampedFraction
}

@Preview
@Composable
fun RegistrationIconsRowPreview() {
    NoterTheme {
        RegistrationIconsRow(
            authIcon = AuthIcon.ICON_5,
            onSelected = {}
        )
    }
}