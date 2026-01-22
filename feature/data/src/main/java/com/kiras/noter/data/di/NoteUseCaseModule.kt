package com.kiras.noter.data.di

import com.kiras.noter.data.ClockProviderImpl
import com.kiras.noter.data.IdProviderImpl
import com.kiras.noter.domain.repository.ClockProvider
import com.kiras.noter.domain.repository.IdProvider
import com.kiras.noter.domain.use_case.NoteUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val noteUseCaseModule = module {
    singleOf(::NoteUseCase)

    singleOf(::IdProviderImpl).bind<IdProvider>()
}