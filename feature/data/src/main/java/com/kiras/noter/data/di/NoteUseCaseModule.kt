package com.kiras.noter.data.di

import com.kiras.noter.domain.use_case.NoteUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val noteUseCaseModule = module {
    singleOf(::NoteUseCase)
}