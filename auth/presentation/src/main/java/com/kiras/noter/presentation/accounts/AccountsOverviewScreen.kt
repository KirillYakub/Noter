package com.kiras.noter.presentation.accounts

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.anim.SlideFadeAnimatedContent
import com.kiras.noter.designsystem.components.EmptyListLabel
import com.kiras.noter.designsystem.components.NoterScaffold
import com.kiras.noter.presentation.accounts.components.AccountsList
import com.kiras.noter.presentation.accounts.components.AccountsStatusBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountsOverviewScreenRoot(
    onBackClick: () -> Unit,
    onAccountClick: (String) -> Unit,
    viewModel: AccountsOverviewViewModel = koinViewModel()
) {
    AccountsOverviewScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is AccountsOverviewAction.OnBackClick -> onBackClick()
                is AccountsOverviewAction.OnAccountClick -> onAccountClick(action.email)
                else -> viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsOverviewScreen(
    state: AccountsOverviewState,
    onAction: (AccountsOverviewAction) -> Unit,
) {
    SlideFadeAnimatedContent(visible = state.showContent) {
        NoterScaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
            topAppBar = {
                AccountsStatusBar(
                    onBackClick = { onAction(AccountsOverviewAction.OnBackClick) }
                )
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(18.dp)
                ) {
                    if(state.accounts.isNotEmpty()) {
                        AccountsList(
                            accounts = state.accounts,
                            onAccountClick = { email ->
                                onAction(
                                    AccountsOverviewAction.OnAccountClick(email)
                                )
                            },
                            onDeleteClick = { id ->
                                onAction(AccountsOverviewAction.OnAccountDelete(id))
                            }
                        )
                    }
                    else {
                        Box(modifier = Modifier.align(Alignment.Center)) {
                            EmptyListLabel()
                        }
                    }
                }
            }
        )
    }
}