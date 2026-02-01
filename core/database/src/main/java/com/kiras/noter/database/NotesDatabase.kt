package com.kiras.noter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kiras.noter.database.converter.AccountIconTypeConverter
import com.kiras.noter.database.converter.NoteColorTypeConverter
import com.kiras.noter.database.dao.AccountsDao
import com.kiras.noter.database.dao.NotesDao
import com.kiras.noter.database.entity.AccountEntity
import com.kiras.noter.database.entity.NoteEntity

@Database(
    entities = [AccountEntity::class, NoteEntity::class],
    version = 1
)
@TypeConverters(
    NoteColorTypeConverter::class,
    AccountIconTypeConverter::class
)
abstract class NotesDatabase: RoomDatabase() {
    abstract val accountsDao: AccountsDao
    abstract val notesDao: NotesDao
}