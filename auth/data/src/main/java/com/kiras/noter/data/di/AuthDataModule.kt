package com.kiras.noter.data.di

import com.kiras.noter.data.EmailPatternValidator
import com.kiras.noter.domain.PatternValidator
import com.kiras.noter.domain.UserDataValidator
import com.kiras.noter.domain.use_case.AuthUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)

    singleOf(::AuthUseCase)
}