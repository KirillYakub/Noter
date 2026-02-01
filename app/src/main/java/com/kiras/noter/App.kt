package com.kiras.noter

import android.app.Application
import com.kiras.noter.data.di.authDataModule
import com.kiras.noter.data.di.coreDataModule
import com.kiras.noter.data.di.noteUseCaseModule
import com.kiras.noter.data.di.passwordHasherModule
import com.kiras.noter.database.di.databaseModule
import com.kiras.noter.di.appModule
import com.kiras.noter.notes.presentation.note_page.di.notePageViewModelModule
import com.kiras.noter.notes.presentation.notes_overview.di.notesOverviewViewModelModule
import com.kiras.noter.presentation.login.di.loginViewModelModule
import com.kiras.noter.presentation.registration.di.registrationViewModelModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

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
                notePageViewModelModule,
                authDataModule,
                registrationViewModelModule,
                loginViewModelModule,
                passwordHasherModule
            )
        }
    }
}