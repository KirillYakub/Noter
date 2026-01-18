package com.kiras.noter.di

import com.kiras.noter.App
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single<CoroutineScope> {
        (androidApplication() as App).applicationScope
    }
}