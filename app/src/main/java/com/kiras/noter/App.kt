package com.kiras.noter

import android.app.Application
import com.kiras.noter.data.di.clockProviderModule
import com.kiras.noter.data.di.coreDataModule
import com.kiras.noter.data.di.noteUseCaseModule
import com.kiras.noter.database.di.databaseModule
import com.kiras.noter.di.appModule
import com.kiras.noter.notes.presentation.note_page.di.notePageViewModelModule
import com.kiras.noter.notes.presentation.notes_overview.di.notesOverviewViewModelModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                databaseModule,
                coreDataModule,
                notesOverviewViewModelModule,
                noteUseCaseModule,
                clockProviderModule,
                notePageViewModelModule
            )
        }
    }
}