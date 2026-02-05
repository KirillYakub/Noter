package com.kiras.noter.presentation.accounts.di

import com.kiras.noter.presentation.accounts.AccountsOverviewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val accountsViewModelModule = module {
    viewModelOf(::AccountsOverviewViewModel)
}