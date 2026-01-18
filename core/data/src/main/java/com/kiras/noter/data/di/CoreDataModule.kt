package com.kiras.noter.data.di

import com.kiras.noter.data.note.OfflineFirstNoteRepositoryImpl
import com.kiras.noter.domain.repository.NotesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    singleOf(::OfflineFirstNoteRepositoryImpl).bind<NotesRepository>()
}