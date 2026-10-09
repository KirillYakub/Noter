package com.kiras.noter.data.di

import com.kiras.noter.domain.use_case.FolderUseCases
import com.kiras.noter.domain.use_case.NoteEditUseCase
import com.kiras.noter.domain.use_case.GetNotesSettingsUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val noteEditUseCaseModule = module {
    singleOf(::NoteEditUseCase)
    singleOf(::GetNotesSettingsUseCase)
    singleOf(::FolderUseCases)
}