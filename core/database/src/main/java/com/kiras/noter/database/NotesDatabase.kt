package com.kiras.noter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kiras.noter.database.converter.AccountIconTypeConverter
import com.kiras.noter.database.converter.AccountNotesSettingsTypeConverter
import com.kiras.noter.database.converter.NoteAlignmentTypeConverter
import com.kiras.noter.database.converter.NoteColorTypeConverter
import com.kiras.noter.database.dao.AccountNotesSettingsDao
import com.kiras.noter.database.dao.AccountsDao
import com.kiras.noter.database.dao.FolderDao
import com.kiras.noter.database.dao.NotesDao
import com.kiras.noter.database.entity.AccountEntity
import com.kiras.noter.database.entity.AccountNotesSettingsEntity
import com.kiras.noter.database.entity.FolderEntity
import com.kiras.noter.database.entity.NoteEntity
import com.kiras.noter.database.entity.NoteFolderCrossRef

@Database(
    entities = [
        AccountEntity::class,
        NoteEntity::class,
        AccountNotesSettingsEntity::class,
        FolderEntity::class,
        NoteFolderCrossRef::class
               ],
    version = 2
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
    abstract val folderDao: FolderDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE notes ADD COLUMN isImportant INTEGER NOT NULL DEFAULT 0")
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS folders (
                        id TEXT NOT NULL PRIMARY KEY,
                        ownerAccountId TEXT NOT NULL,
                        name TEXT NOT NULL,
                        createTime INTEGER NOT NULL,
                        FOREIGN KEY(ownerAccountId) REFERENCES accounts(id) ON DELETE CASCADE
                    )
                """)
                db.execSQL("CREATE INDEX IF NOT EXISTS index_folders_ownerAccountId ON folders(ownerAccountId)")
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS note_folders (
                        noteId TEXT NOT NULL,
                        folderId TEXT NOT NULL,
                        PRIMARY KEY(noteId, folderId),
                        FOREIGN KEY(noteId) REFERENCES notes(id) ON DELETE CASCADE,
                        FOREIGN KEY(folderId) REFERENCES folders(id) ON DELETE CASCADE
                    )
                """)
                db.execSQL("CREATE INDEX IF NOT EXISTS index_note_folders_noteId ON note_folders(noteId)")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_note_folders_folderId ON note_folders(folderId)")
            }
        }
    }
}