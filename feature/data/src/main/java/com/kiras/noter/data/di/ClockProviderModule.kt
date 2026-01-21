package com.kiras.noter.data.di

import com.kiras.noter.data.ClockProviderImpl
import com.kiras.noter.domain.repository.ClockProvider
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val clockProviderModule = module {
    singleOf(::ClockProviderImpl).bind<ClockProvider>()
}