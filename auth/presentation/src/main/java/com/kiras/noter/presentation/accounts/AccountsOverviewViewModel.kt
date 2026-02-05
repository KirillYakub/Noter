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

class AccountsOverviewViewModel(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    var state by mutableStateOf(AccountsOverviewState())
        private set

    init {
        accountsRepository.getAccounts().onEach { accounts ->
            val accountsUi = accounts.map { it.toAccountUi() }
            state = state.copy(accounts = accountsUi)
        }.launchIn(viewModelScope)
    }
}