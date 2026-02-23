package com.kiras.noter.presentation.accounts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kiras.noter.domain.util.extensions.takeFirst
import com.kiras.noter.presentation.accounts.model.AccountUi

@Composable
fun AccountsList(
    modifier: Modifier = Modifier,
    accounts: List<AccountUi>,
    onAccountClick: (email: String) -> Unit,
    onDeleteClick: (id: String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = accounts, key = { it.id }) { account ->
            AccountItem(
                name = account.name.takeFirst(),
                lastSignIn = account.lastSignIn,
                icon = account.icon,
                onClick = { onAccountClick(account.email) },
                onDeleteClick = { onDeleteClick(account.id) }
            )
        }
    }
}