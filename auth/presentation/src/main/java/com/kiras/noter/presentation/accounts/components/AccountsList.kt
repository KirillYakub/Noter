package com.kiras.noter.presentation.accounts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kiras.noter.domain.notes.model.NoteColor
import com.kiras.noter.presentation.accounts.model.AccountUi
import com.kiras.noter.ui.getColorForUiTheme

@Composable
fun AccountsList(
    modifier: Modifier = Modifier,
    accounts: List<AccountUi>,
    onNoteClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = accounts, key = { it.id }) { account ->
            AccountItem(
                name = account.name,
                lastSignIn = account.lastSignIn,
                icon = account.icon,
                onClick = { onNoteClick(account.id) }
            )
        }
    }
}