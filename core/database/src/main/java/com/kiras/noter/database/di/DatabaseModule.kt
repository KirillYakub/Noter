package com.kiras.noter.database.di

import androidx.room.Room
import com.kiras.noter.database.NotesDatabase
import com.kiras.noter.database.RoomAccountLocalDataSourceImpl
import com.kiras.noter.database.RoomAccountNotesSettingsLocalDataSourceImpl
import com.kiras.noter.database.RoomNotesLocalDataSourceImpl
import com.kiras.noter.database.dao.AccountNotesSettingsDao
import com.kiras.noter.database.dao.AccountsDao
import com.kiras.noter.database.dao.NotesDao
import com.kiras.noter.domain.accounts.repository.AccountsLocalDataSource
import com.kiras.noter.domain.accounts.repository.settings.AccountNotesSettingsLocalDataSource
import com.kiras.noter.domain.notes.repository.NotesLocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
    single<NotesDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            NotesDatabase::class.java,
            "notes_db"
        ).build()
    }
    single<NotesDao> {
        get<NotesDatabase>().notesDao
    }
    single<AccountsDao> {
        get<NotesDatabase>().accountsDao
    }
    single<AccountNotesSettingsDao> {
        get<NotesDatabase>().notesSettingsDao
    }

    singleOf(::RoomAccountNotesSettingsLocalDataSourceImpl)
        .bind<AccountNotesSettingsLocalDataSource>()
    singleOf(::RoomNotesLocalDataSourceImpl)
        .bind<NotesLocalDataSource>()
    singleOf(::RoomAccountLocalDataSourceImpl)
        .bind<AccountsLocalDataSource>()
}