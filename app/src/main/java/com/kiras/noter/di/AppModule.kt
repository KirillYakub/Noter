package com.kiras.noter.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.kiras.noter.App
import com.kiras.noter.MainViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<DataStore<Preferences>> {
        val context: Context = get()
        PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("auth_preferences") }
        )
    }

    single<CoroutineScope> {
        (androidApplication() as App).applicationScope
    }
    viewModelOf(::MainViewModel)
}