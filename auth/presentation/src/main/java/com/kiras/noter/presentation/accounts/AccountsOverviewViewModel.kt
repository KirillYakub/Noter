package com.kiras.noter.presentation.accounts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kiras.noter.domain.accounts.repository.AccountsRepository
import com.kiras.noter.presentation.accounts.mapper.toAccountUi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AccountsOverviewViewModel(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    var state by mutableStateOf(AccountsOverviewState())
        private set

    init {
        accountsRepository.getAccounts().onEach { accounts ->
            val accountsUi = accounts.map { it.toAccountUi() }
            state = state.copy(
                showContent = true,
                accounts = accountsUi
            )
        }.launchIn(viewModelScope)
    }

    fun onAction(action: AccountsOverviewAction) {
        when(action) {
            is AccountsOverviewAction.OnAccountDelete -> {
                viewModelScope.launch {
                    accountsRepository.deleteAccount(action.id)
                }
            }
            else -> Unit
        }
    }
}