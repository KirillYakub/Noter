package com.kiras.noter.presentation.accounts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.getAuthTextFieldsColor
import com.kiras.noter.designsystem.getAuthTextFieldsTextColor
import com.kiras.noter.domain.accounts.model.AccountIcon
import com.kiras.noter.presentation.R
import com.kiras.noter.ui.getAccountIcon

@Composable
fun AccountItem(
    modifier: Modifier = Modifier,
    name: String,
    lastSignIn: String,
    icon: AccountIcon,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = getAuthTextFieldsColor
        ),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                imageVector = icon.getAccountIcon(),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = getAuthTextFieldsTextColor
                )
                Text(
                    text = stringResource(R.string.last_sign_in, lastSignIn),
                    style = MaterialTheme.typography.bodySmall,
                    color = getAuthTextFieldsTextColor.copy(alpha = 0.8f)
                )
            }
        }
    }
}

@Preview
@Composable
fun AccountListItemPreview() {
    NoterTheme {
        AccountItem(
            name = "Kyrylo",
            lastSignIn = "12.01.2026",
            icon = AccountIcon.ICON_1,
            onClick = { }
        )
    }
}