package com.kiras.noter.presentation.accounts

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.components.NoterScaffold
import com.kiras.noter.presentation.accounts.components.AccountsList
import com.kiras.noter.presentation.accounts.components.AccountsStatusBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountsOverviewScreenRoot(
    onBackClick: () -> Unit,
    onAccountDelete: (String) -> Unit,
    onAccountClick: (String) -> Unit,
    viewModel: AccountsOverviewViewModel = koinViewModel()
) {
    AccountsOverviewScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is AccountsOverviewAction.OnBackClick -> onBackClick()
                is AccountsOverviewAction.OnAccountDelete -> onAccountDelete
                is AccountsOverviewAction.OnAccountClick -> onAccountClick
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
    NoterScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = if(isSystemInDarkTheme()) Color.Black else Color.White,
        topAppBar = {
            AccountsStatusBar(
                onBackClick = { onAction(AccountsOverviewAction.OnBackClick) }
            )
        },
        content = { paddingValues ->
            AccountsList(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(18.dp),
                accounts = state.accounts,
                onNoteClick = { id -> onAction(AccountsOverviewAction.OnAccountClick(id)) }
            )
        }
    )
}