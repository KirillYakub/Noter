package com.kiras.noter.presentation.registration

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.components.NoterScaffold
import com.kiras.noter.presentation.R
import com.kiras.noter.designsystem.components.NoterActionButton
import com.kiras.noter.presentation.components.AuthEmailTextField
import com.kiras.noter.presentation.components.AuthPasswordTextField
import com.kiras.noter.presentation.registration.components.PasswordRequirement
import com.kiras.noter.presentation.registration.components.RegistrationIconsRow
import com.kiras.noter.presentation.registration.components.RegistrationTopBar
import com.kiras.noter.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreenRoot(
    onLoginClick: () -> Unit,
    onSuccessfulRegistrationClick: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is RegisterEvent.Error -> {
                keyboardController?.hide()
                Toast.makeText(context, event.error.asString(context), Toast.LENGTH_LONG).show()
            }
            RegisterEvent.RegistrationSuccess -> {
                Toast.makeText(context, R.string.registration_success, Toast.LENGTH_LONG).show()
                onSuccessfulRegistrationClick()
            }
        }
    }
    RegisterScreen(
        state = viewModel.state,
        onAction = { action ->
            when(action) {
                is RegisterAction.OnLoginClick -> onLoginClick()
                else -> viewModel.onAction(action)
            }
        }
    )
}

@Composable
private fun RegisterScreen(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit
) {
    NoterScaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = if(isSystemInDarkTheme()) Color.Black else Color.White,
        topAppBar = {
            RegistrationTopBar(
                onLoginClick = { onAction(RegisterAction.OnLoginClick) }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                RegistrationIconsRow(
                    modifier = Modifier.padding(top = 25.dp),
                    authIcon = state.authIcon,
                    onSelected = { onAction(RegisterAction.OnAuthIconChange(it)) }
                )
                AuthEmailTextField(
                    state = state.email,
                    hint = stringResource(id = R.string.your_email),
                    title = stringResource(id = R.string.email),
                    modifier = Modifier.padding(top = 20.dp)
                )
                AuthPasswordTextField(
                    state = state.password,
                    isPasswordVisible = state.isPasswordVisible,
                    onTogglePasswordVisibility = {
                        onAction(RegisterAction.OnTogglePasswordVisibilityClick)
                    },
                    hint = stringResource(id = R.string.password),
                    title = stringResource(id = R.string.password),
                    modifier = Modifier.padding(top = 20.dp)
                )
                PasswordRequirement(
                    text = stringResource(id = R.string.at_least_8_characters),
                    isValid = state.passwordValidationState.hasMinLength,
                    modifier = Modifier.padding(top = 25.dp)
                )
                PasswordRequirement(
                    text = stringResource(id = R.string.at_least_one_number),
                    isValid = state.passwordValidationState.hasNumber,
                    modifier = Modifier.padding(top = 15.dp)
                )
                PasswordRequirement(
                    text = stringResource(id = R.string.contains_lowercase_character),
                    isValid = state.passwordValidationState.hasLowerCaseCharacter,
                    modifier = Modifier.padding(top = 15.dp)
                )
                PasswordRequirement(
                    text = stringResource(id = R.string.contains_uppercase_character),
                    isValid = state.passwordValidationState.hasUpperCaseCharacter,
                    modifier = Modifier.padding(top = 15.dp)
                )
                NoterActionButton(
                    text = stringResource(id = R.string.register),
                    isLoading = state.isRegistering,
                    enabled = state.canRegister,
                    onClick = { onAction(RegisterAction.OnRegisterClick) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp)
                        .padding(top = 40.dp)
                )
            }
        }
    )
}

@Preview
@Composable
fun RegisterScreenPreview() {
    NoterTheme {
        RegisterScreen(
            state = RegisterState(),
            onAction = {}
        )
    }
}