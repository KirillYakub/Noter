package com.kiras.noter.presentation.settings.di

import com.kiras.noter.presentation.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsViewModelModule = module {
    viewModelOf(::SettingsViewModel)
}