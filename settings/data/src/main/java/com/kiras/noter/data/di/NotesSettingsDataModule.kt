package com.kiras.noter.data.di

import com.kiras.noter.domain.use_case.LogoutUseCase
import com.kiras.noter.domain.use_case.NotesSettingsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val notesSettingsDataModule = module {
    singleOf(::LogoutUseCase)
    singleOf(::NotesSettingsUseCase)
}