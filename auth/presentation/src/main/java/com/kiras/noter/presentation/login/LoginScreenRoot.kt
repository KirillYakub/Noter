package com.kiras.noter.presentation.login

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.kiras.noter.designsystem.Grey4
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.components.NoterScaffold
import com.kiras.noter.designsystem.getLoginButtonColor
import com.kiras.noter.presentation.R
import com.kiras.noter.designsystem.components.NoterActionButton
import com.kiras.noter.presentation.components.AuthEmailTextField
import com.kiras.noter.presentation.components.AuthPasswordTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenRoot(
    onRegisterClick: () -> Unit,
    onSuccessfulLoginClick: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    LoginScreen(
        state = viewModel.state,
        onAction = { action ->
            when(action) {
                is LoginAction.OnRegisterClick -> onRegisterClick()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    NoterScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = if(isSystemInDarkTheme()) Color.Black else Color.White,
        topAppBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.hi_there),
                        style = MaterialTheme.typography.headlineMedium,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                AuthEmailTextField(
                    state = state.email,
                    hint = stringResource(id = R.string.your_email),
                    title = stringResource(id = R.string.email),
                    modifier = Modifier.padding(top = 25.dp)
                )
                AuthPasswordTextField(
                    state = state.password,
                    isPasswordVisible = state.isPasswordVisible,
                    onTogglePasswordVisibility = {
                        onAction(LoginAction.OnTogglePasswordVisibility)
                    },
                    hint = stringResource(id = R.string.password),
                    title = stringResource(id = R.string.password),
                    modifier = Modifier.padding(top = 20.dp)
                )
                NoterActionButton(
                    text = stringResource(id = R.string.login),
                    isLoading = state.isLoggingIn,
                    enabled = state.canLogin,
                    onClick = { onAction(LoginAction.OnLoginClick) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp)
                        .padding(top = 40.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                ) {
                    Text(
                        text = stringResource(R.string.do_not_have_an_account),
                        style = MaterialTheme.typography.titleSmall,
                        color = if (isSystemInDarkTheme()) Grey1 else Grey4
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Card(
                        onClick = { onAction(LoginAction.OnRegisterClick) },
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = getLoginButtonColor
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.sign_up),
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Black,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    NoterTheme {
        LoginScreen(
            state = LoginState(),
            onAction = {}
        )
    }
}