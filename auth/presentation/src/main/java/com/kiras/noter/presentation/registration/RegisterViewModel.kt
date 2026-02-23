package com.kiras.noter.presentation.registration

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiras.noter.domain.UserDataValidator
import com.kiras.noter.domain.use_case.AuthUseCase
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.Result
import com.kiras.noter.ui.R
import com.kiras.noter.ui.UiText
import com.kiras.noter.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

fun TextFieldState.textAsFlow() = snapshotFlow { text }

class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        state.email.textAsFlow().onEach { email ->
            val isEmailValid = userDataValidator.isValidEmail(email.toString())
            state = state.copy(
                isEmailValid = isEmailValid,
                canRegister = isEmailValid && state.isNameValid
                        && state.passwordValidationState.isValidPassword && !state.isRegistering
            )
        }.launchIn(viewModelScope)

        state.name.textAsFlow().onEach { name ->
            val isNameValid = userDataValidator.isValidName(name.toString())
            state = state.copy(
                isNameValid = isNameValid,
                nameLength = name.length,
                canRegister = isNameValid && state.isEmailValid
                        && state.passwordValidationState.isValidPassword && !state.isRegistering
            )
        }.launchIn(viewModelScope)

        state.password.textAsFlow().onEach { password ->
            val passwordValidationState = userDataValidator.validatePassword(password.toString())
            state = state.copy(
                passwordValidationState = passwordValidationState,
                canRegister = state.isEmailValid && state.isNameValid &&
                        passwordValidationState.isValidPassword && !state.isRegistering
            )
        }.launchIn(viewModelScope)
    }

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnAuthIconChange -> {
                state = state.copy(accountIcon = action.accountIcon)
            }
            RegisterAction.OnRegisterClick -> register()
            RegisterAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(isPasswordVisible = !state.isPasswordVisible)
            }
            else -> Unit
        }
    }

    private fun register() {
        viewModelScope.launch {
            state = state.copy(isRegistering = true)
            val result = authUseCase.register(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString(),
                name = state.name.text.toString().trim(),
                icon = state.accountIcon
            )
            state = state.copy(isRegistering = false)
            when(result) {
                is Result.Error -> {
                    when(result.error) {
                        DataError.Local.DISC_FULL -> {
                            eventChannel.send(RegisterEvent.Error(
                                UiText.StringResource(R.string.error_disc_full)
                            ))
                        }
                        DataError.Local.CONFLICT -> {
                            eventChannel.send(RegisterEvent.Error(
                                UiText.StringResource(R.string.error_email_already_exists)
                            ))
                        }
                        else -> {
                            eventChannel.send(RegisterEvent.Error(result.error.asUiText()))
                        }
                    }
                }
                is Result.Success -> {
                    eventChannel.send(RegisterEvent.RegistrationSuccess)
                }
            }
        }
    }
}