package com.kiras.noter.database.di

import androidx.room.Room
import com.kiras.noter.database.NotesDatabase
import com.kiras.noter.database.RoomNotesLocalDataSourceImpl
import com.kiras.noter.domain.repository.NotesLocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            NotesDatabase::class.java,
            "notes_db"
        ).build()
    }
    single {
        get<NotesDatabase>().notesDao
    }

    single<NotesLocalDataSource> {
        RoomNotesLocalDataSourceImpl(
            notesDao = get()
        )
    }
}