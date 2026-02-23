package com.kiras.noter.presentation.login

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.kiras.noter.domain.UserDataValidator
import com.kiras.noter.domain.use_case.AuthUseCase
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.Result
import com.kiras.noter.presentation.registration.textAsFlow
import com.kiras.noter.presentation.util.Login
import com.kiras.noter.ui.R
import com.kiras.noter.ui.UiText
import com.kiras.noter.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

fun String.toTextFieldState() = TextFieldState(initialText = this)

class LoginViewModel(
    private val userDataValidator: UserDataValidator,
    private val authUseCase: AuthUseCase,
    saveStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        val args = saveStateHandle.toRoute<Login>()
        args.email?.let {
            state = state.copy(email = it.toTextFieldState())
        }
        combine(
            flow = state.email.textAsFlow(),
            flow2 = state.password.textAsFlow()
        ) { email, password ->
            state = state.copy(
                canLogin = userDataValidator.isValidEmail(email.toString().trim()) &&
                        password.isNotEmpty()
            )
        }.launchIn(viewModelScope)
    }

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.OnLoginClick -> login()
            LoginAction.OnTogglePasswordVisibility -> {
                state = state.copy(isPasswordVisible = !state.isPasswordVisible)
            }
            else -> Unit
        }
    }

    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)
            val result = authUseCase.login(
                email = state.email.text.toString().trim(),
                password = state.password.text.toString(),
            )
            state = state.copy(isLoggingIn = false)
            when(result) {
                is Result.Error -> {
                    when(result.error) {
                        DataError.Local.UNAUTHORIZED -> {
                            eventChannel.send(LoginEvent.Error(
                                UiText.StringResource(R.string.error_account_not_exist)
                            ))
                        }
                        else -> {
                            eventChannel.send(LoginEvent.Error(result.error.asUiText()))
                        }
                    }
                }
                is Result.Success -> {
                    eventChannel.send(LoginEvent.LoginSuccess)
                }
            }
        }
    }
}