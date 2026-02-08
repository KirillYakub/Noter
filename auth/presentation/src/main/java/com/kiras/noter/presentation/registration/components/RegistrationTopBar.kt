package com.kiras.noter.presentation.registration.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.kiras.noter.designsystem.Grey5
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.getLoginButtonColor
import com.kiras.noter.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationTopBar(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Column {
                Text(
                    text = stringResource(R.string.create_account),
                    style = MaterialTheme.typography.headlineMedium,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.already_have_an_account),
                        style = MaterialTheme.typography.titleSmall,
                        color = if (isSystemInDarkTheme()) Grey1 else Grey5
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Card(
                        onClick = onLoginClick,
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = getLoginButtonColor
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.login),
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Black,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun RegistrationTopBarPreview() {
    NoterTheme {
        RegistrationTopBar(
            onLoginClick = {}
        )
    }
}