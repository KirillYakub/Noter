package com.kiras.noter.presentation.registration.di

import com.kiras.noter.presentation.registration.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val registrationViewModelModule = module {
    viewModelOf(::RegisterViewModel)
}