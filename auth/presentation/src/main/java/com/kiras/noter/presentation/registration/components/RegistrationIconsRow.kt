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
import com.kiras.noter.designsystem.Grey4
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.domain.accounts.model.AccountIcon
import com.kiras.noter.ui.getAccountIcon
import kotlinx.coroutines.delay
import kotlin.math.abs
import com.kiras.noter.presentation.R

@Composable
fun RegistrationIconsRow(
    modifier: Modifier = Modifier,
    accountIcon: AccountIcon,
    onSelected: (AccountIcon) -> Unit
) {

    val pagerState = rememberPagerState(
        initialPage = accountIcon.ordinal,
        pageCount = { AccountIcon.entries.size }
    )

    LaunchedEffect(pagerState.currentPage) {
        delay(300)
        onSelected(AccountIcon.entries[pagerState.currentPage])
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
            color = if(isSystemInDarkTheme()) Color.White else Grey4
        )
        BoxWithConstraints(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val pageMaxSize = 80.dp
            val sidePadding = remember {
                ((maxWidth - pageMaxSize) / 2).coerceAtLeast(0.dp)
            }

            HorizontalPager(
                state = pagerState,
                key = { id -> AccountIcon.entries[id].name },
                pageSize = PageSize.Fixed(pageSize = pageMaxSize),
                contentPadding = PaddingValues(horizontal = sidePadding),
                flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) { page ->

                val dist by remember(pagerState, page) {
                    derivedStateOf {
                        abs(pagerState.getOffsetDistanceInPages(page))
                    }
                }
                val size by remember(dist) {
                    derivedStateOf {
                        when {
                            dist <= 1f -> lerpDp(80.dp, 60.dp, dist)
                            dist <= 2f -> lerpDp(60.dp, 40.dp, dist - 1f)
                            else -> 40.dp
                        }
                    }
                }
                val alpha by remember(dist) {
                    derivedStateOf {
                        when {
                            dist <= 1f -> 1f
                            dist <= 2f -> 0.8f
                            else -> 0.6f
                        }
                    }
                }

                Box(
                    modifier = Modifier.size(pageMaxSize),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = AccountIcon.entries[page].getAccountIcon(),
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
            accountIcon = AccountIcon.ICON_5,
            onSelected = {}
        )
    }
}