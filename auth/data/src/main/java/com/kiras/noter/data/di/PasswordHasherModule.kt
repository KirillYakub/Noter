package com.kiras.noter.data.di

import com.kiras.noter.data.PasswordHasherImpl
import com.kiras.noter.domain.repository.PasswordHasher
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val passwordHasherModule = module {
    singleOf(::PasswordHasherImpl).bind<PasswordHasher>()
}