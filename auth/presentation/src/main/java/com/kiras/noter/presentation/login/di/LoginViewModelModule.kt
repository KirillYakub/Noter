package com.kiras.noter.presentation.login.di

import com.kiras.noter.presentation.login.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginViewModelModule = module {
    viewModelOf(::LoginViewModel)
}