package com.kiras.noter.presentation.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.Grey1
import com.kiras.noter.designsystem.Grey2
import com.kiras.noter.designsystem.Grey5
import com.kiras.noter.designsystem.LogoDay
import com.kiras.noter.designsystem.LogoNight
import com.kiras.noter.designsystem.NameIcon
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.components.NoterActionButton
import com.kiras.noter.designsystem.components.NoterOutlinedActionButton
import com.kiras.noter.designsystem.components.NoterScaffold
import com.kiras.noter.presentation.R

@Composable
fun IntroScreenRoot(
    onAccountsClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    IntroScreen(
        onAction = { action ->
            when(action) {
                IntroAction.OnAccountsClick -> onAccountsClick()
                IntroAction.OnLoginClick -> onLoginClick()
                IntroAction.OnRegisterClick -> onRegisterClick()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntroScreen(onAction: (IntroAction) -> Unit) {
    NoterScaffold(
        topAppBar = {
            TopAppBar(
                title = {},
                actions = {
                    IconButton(
                        onClick = { onAction(IntroAction.OnAccountsClick) }
                    ) {
                        Icon(
                            imageVector = NameIcon,
                            contentDescription = stringResource(R.string.accounts_icon),
                            tint = if (isSystemInDarkTheme()) Grey2 else Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = if(isSystemInDarkTheme()) Color.Black else Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    imageVector = if (isSystemInDarkTheme()) LogoNight else LogoDay,
                    contentDescription = stringResource(R.string.logo),
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.3f))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 14.dp)
                    .padding(bottom = 48.dp)
            ) {
                Text(
                    text = stringResource(R.string.welcome_to_noter),
                    style = MaterialTheme.typography.headlineMedium,
                    color = if(isSystemInDarkTheme()) Grey1 else Grey5
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.note_description),
                    style = MaterialTheme.typography.bodySmall,
                    color = if(isSystemInDarkTheme()) Grey1 else Grey5
                )
                Spacer(modifier = Modifier.height(32.dp))
                NoterOutlinedActionButton(
                    text = stringResource(R.string.login),
                    isLoading = false,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onAction(IntroAction.OnLoginClick) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                NoterActionButton(
                    text = stringResource(R.string.sign_up),
                    isLoading = false,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onAction(IntroAction.OnRegisterClick) }
                )
            }
        }
    }
}

@Preview
@Composable
fun IntroScreenPreview() {
    NoterTheme {
        IntroScreen { }
    }
}