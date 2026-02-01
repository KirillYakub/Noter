package com.kiras.noter.data.di

import com.kiras.noter.data.ClockProviderImpl
import com.kiras.noter.data.IdProviderImpl
import com.kiras.noter.data.auth.SessionStorageImpl
import com.kiras.noter.data.offline_first.account.OfflineFirstAccountRepositoryImpl
import com.kiras.noter.data.offline_first.note.OfflineFirstNoteRepositoryImpl
import com.kiras.noter.domain.ClockProvider
import com.kiras.noter.domain.IdProvider
import com.kiras.noter.domain.SessionStorage
import com.kiras.noter.domain.accounts.repository.AccountsRepository
import com.kiras.noter.domain.notes.repository.NotesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    singleOf(::SessionStorageImpl).bind<SessionStorage>()
    singleOf(::OfflineFirstNoteRepositoryImpl).bind<NotesRepository>()
    singleOf(::OfflineFirstAccountRepositoryImpl).bind<AccountsRepository>()

    singleOf(::IdProviderImpl).bind<IdProvider>()
    singleOf(::ClockProviderImpl).bind<ClockProvider>()
}