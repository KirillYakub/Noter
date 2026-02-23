package com.kiras.noter.presentation.registration

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kiras.noter.designsystem.EmailIcon
import com.kiras.noter.designsystem.Grey4
import com.kiras.noter.designsystem.NameIcon
import com.kiras.noter.designsystem.NoterTheme
import com.kiras.noter.designsystem.components.NoterScaffold
import com.kiras.noter.presentation.R
import com.kiras.noter.designsystem.components.NoterActionButton
import com.kiras.noter.presentation.components.AuthTextField
import com.kiras.noter.presentation.components.AuthPasswordTextField
import com.kiras.noter.presentation.registration.components.PasswordRequirement
import com.kiras.noter.presentation.registration.components.RegistrationIconsRow
import com.kiras.noter.presentation.registration.components.RegistrationTopBar
import com.kiras.noter.presentation.util.Constants.MAX_NAME_LENGTH
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
                onLoginClick = { onAction(RegisterAction.OnLoginClick) },
                modifier = Modifier.padding(top = 10.dp)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                RegistrationIconsRow(
                    modifier = Modifier.padding(top = 25.dp),
                    accountIcon = state.accountIcon,
                    onSelected = { onAction(RegisterAction.OnAuthIconChange(it)) }
                )
                AuthTextField(
                    state = state.name,
                    icon = NameIcon,
                    maxLength = MAX_NAME_LENGTH,
                    hint = stringResource(id = R.string.your_name),
                    titleContent = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(id = R.string.name),
                                style = MaterialTheme.typography.titleSmall,
                                color = if (isSystemInDarkTheme()) Color.White else Grey4
                            )
                            Text(
                                text = "${state.nameLength} / $MAX_NAME_LENGTH",
                                style = MaterialTheme.typography.titleSmall,
                                color = if (isSystemInDarkTheme()) Color.White else Grey4
                            )
                        }
                    },
                    modifier = Modifier.padding(top = 20.dp)
                )
                AuthTextField(
                    state = state.email,
                    icon = EmailIcon,
                    hint = stringResource(id = R.string.email),
                    titleContent = {
                        Text(
                            text = stringResource(id = R.string.email),
                            style = MaterialTheme.typography.titleSmall,
                            color = if (isSystemInDarkTheme()) Color.White else Grey4
                        )
                    },
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
                        .padding(
                            top = 40.dp,
                            bottom = 20.dp
                        )
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