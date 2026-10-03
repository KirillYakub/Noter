package com.kiras.noter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kiras.noter.database.converter.AccountIconTypeConverter
import com.kiras.noter.database.converter.AccountNotesSettingsTypeConverter
import com.kiras.noter.database.converter.NoteAlignmentTypeConverter
import com.kiras.noter.database.converter.NoteColorTypeConverter
import com.kiras.noter.database.dao.AccountNotesSettingsDao
import com.kiras.noter.database.dao.AccountsDao
import com.kiras.noter.database.dao.NotesDao
import com.kiras.noter.database.entity.AccountEntity
import com.kiras.noter.database.entity.AccountNotesSettingsEntity
import com.kiras.noter.database.entity.NoteEntity

@Database(
    entities = [
        AccountEntity::class,
        NoteEntity::class,
        AccountNotesSettingsEntity::class
               ],
    version = 1
)
@TypeConverters(
    NoteAlignmentTypeConverter::class,
    NoteColorTypeConverter::class,
    AccountIconTypeConverter::class,
    AccountNotesSettingsTypeConverter::class
)
abstract class NotesDatabase: RoomDatabase() {
    abstract val accountsDao: AccountsDao
    abstract val notesDao: NotesDao
    abstract val notesSettingsDao: AccountNotesSettingsDao

}