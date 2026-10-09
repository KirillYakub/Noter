package com.kiras.noter.database

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteFullException
import androidx.room.withTransaction
import com.kiras.noter.database.dao.FolderDao
import com.kiras.noter.database.dao.NotesDao
import com.kiras.noter.database.entity.NoteEntity
import com.kiras.noter.database.mappers.toNote
import com.kiras.noter.database.mappers.toNoteEntity
import com.kiras.noter.domain.notes.model.Note
import com.kiras.noter.domain.notes.model.settings.NotesSortType
import com.kiras.noter.domain.notes.repository.NoteId
import com.kiras.noter.domain.notes.repository.NotesLocalDataSource
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomNotesLocalDataSourceImpl(
    private val notesDatabase: NotesDatabase
): NotesLocalDataSource {

    private val notesDao = notesDatabase.notesDao
    private val folderDao = notesDatabase.folderDao

    override fun getNotes(
        ownerAccountId: String,
        query: String,
        sortType: NotesSortType
    ): Flow<List<Note>> {
        return when {
            query.isBlank() && sortType == NotesSortType.DATE ->
                notesDao.getAllNotesByDate(ownerAccountId)
            query.isBlank() && sortType == NotesSortType.ALPHABETICALLY ->
                notesDao.getAllNotesAlphabetically(ownerAccountId)
            query.isNotBlank() && sortType == NotesSortType.DATE ->
                notesDao.searchNotesByDate(ownerAccountId, query)
            else ->
                notesDao.searchNotesAlphabetically(ownerAccountId, query)
        }.map { list ->
            list.map { entity ->
                val folderIds = folderDao.getFolderIdsForNote(entity.id)
                entity.toNote(folderIds)
            }
        }
    }

    override fun getNotesByDay(
        ownerAccountId: String,
        dayStart: Long,
        dayEnd: Long,
        query: String,
        sortType: NotesSortType
    ): Flow<List<Note>> {
        return when {
            query.isBlank() && sortType == NotesSortType.DATE ->
                notesDao.getNotesByDayByDate(ownerAccountId, dayStart, dayEnd)
            query.isBlank() && sortType == NotesSortType.ALPHABETICALLY ->
                notesDao.getNotesByDayAlphabetically(ownerAccountId, dayStart, dayEnd)
            query.isNotBlank() && sortType == NotesSortType.DATE ->
                notesDao.searchNotesByDayByDate(ownerAccountId, dayStart, dayEnd, query)
            else ->
                notesDao.searchNotesByDayAlphabetically(ownerAccountId, dayStart, dayEnd, query)
        }.map { list ->
            list.map { entity ->
                val folderIds = folderDao.getFolderIdsForNote(entity.id)
                entity.toNote(folderIds)
            }
        }
    }

    override fun getNotesByFolder(
        ownerAccountId: String,
        folderId: String,
        query: String,
        sortType: NotesSortType
    ): Flow<List<Note>> {
        return when {
            query.isBlank() && sortType == NotesSortType.DATE ->
                notesDao.getNotesByFolderByDate(ownerAccountId, folderId)
            query.isBlank() && sortType == NotesSortType.ALPHABETICALLY ->
                notesDao.getNotesByFolderAlphabetically(ownerAccountId, folderId)
            query.isNotBlank() && sortType == NotesSortType.DATE ->
                notesDao.searchNotesByFolderByDate(ownerAccountId, folderId, query)
            else ->
                notesDao.searchNotesByFolderAlphabetically(ownerAccountId, folderId, query)
        }.map { list ->
            list.map { entity ->
                val folderIds = folderDao.getFolderIdsForNote(entity.id)
                entity.toNote(folderIds)
            }
        }
    }

    override fun getImportantNotes(
        ownerAccountId: String,
        query: String,
        sortType: NotesSortType
    ): Flow<List<Note>> {
        return when {
            query.isBlank() && sortType == NotesSortType.DATE ->
                notesDao.getImportantNotesByDate(ownerAccountId)
            query.isBlank() && sortType == NotesSortType.ALPHABETICALLY ->
                notesDao.getImportantNotesAlphabetically(ownerAccountId)
            query.isNotBlank() && sortType == NotesSortType.DATE ->
                notesDao.searchImportantNotesByDate(ownerAccountId, query)
            else ->
                notesDao.searchImportantNotesAlphabetically(ownerAccountId, query)
        }.map { list ->
            list.map { entity ->
                val folderIds = folderDao.getFolderIdsForNote(entity.id)
                entity.toNote(folderIds)
            }
        }
    }

    override fun getNotesByDayAndFolder(
        ownerAccountId: String,
        folderId: String,
        dayStart: Long,
        dayEnd: Long,
        query: String,
        sortType: NotesSortType
    ): Flow<List<Note>> {
        return when {
            query.isBlank() && sortType == NotesSortType.DATE ->
                notesDao.getNotesByDayAndFolderByDate(ownerAccountId, folderId, dayStart, dayEnd)
            query.isBlank() && sortType == NotesSortType.ALPHABETICALLY ->
                notesDao.getNotesByDayAndFolderAlphabetically(ownerAccountId, folderId, dayStart, dayEnd)
            query.isNotBlank() && sortType == NotesSortType.DATE ->
                notesDao.searchNotesByDayAndFolderByDate(ownerAccountId, folderId, dayStart, dayEnd, query)
            else ->
                notesDao.searchNotesByDayAndFolderAlphabetically(ownerAccountId, folderId, dayStart, dayEnd, query)
        }.map { list ->
            list.map { entity ->
                val folderIds = folderDao.getFolderIdsForNote(entity.id)
                entity.toNote(folderIds)
            }
        }
    }

    override fun getImportantNotesByDay(
        ownerAccountId: String,
        dayStart: Long,
        dayEnd: Long,
        query: String,
        sortType: NotesSortType
    ): Flow<List<Note>> {
        return when {
            query.isBlank() && sortType == NotesSortType.DATE ->
                notesDao.getImportantNotesByDayByDate(ownerAccountId, dayStart, dayEnd)
            query.isBlank() && sortType == NotesSortType.ALPHABETICALLY ->
                notesDao.getImportantNotesByDayAlphabetically(ownerAccountId, dayStart, dayEnd)
            query.isNotBlank() && sortType == NotesSortType.DATE ->
                notesDao.searchImportantNotesByDayByDate(ownerAccountId, dayStart, dayEnd, query)
            else ->
                notesDao.searchImportantNotesByDayAlphabetically(ownerAccountId, dayStart, dayEnd, query)
        }.map { list ->
            list.map { entity ->
                val folderIds = folderDao.getFolderIdsForNote(entity.id)
                entity.toNote(folderIds)
            }
        }
    }

    override fun getActiveUserNotesCount(ownerAccountId: String): Flow<Int> {
        return notesDao.getNotesCount(ownerAccountId)
    }

    override suspend fun getNote(ownerAccountId: String, id: NoteId): Note {
        val entity = notesDao.getNoteById(ownerAccountId, id)
        val folderIds = folderDao.getFolderIdsForNote(entity.id)
        return entity.toNote(folderIds)
    }

    override suspend fun upsertNote(note: Note): Result<NoteId, DataError.Local> {
        return try {
            val noteAsEntity = note.toNoteEntity()
            notesDatabase.withTransaction {
                notesDao.upsertNote(noteAsEntity)
                folderDao.updateNoteFolders(note.id, note.folderIds)
            }
            Result.Success(noteAsEntity.id)
        } catch (_: SQLiteFullException) {
            Result.Error(DataError.Local.DISC_FULL)
        } catch (_: SQLiteConstraintException) {
            Result.Error(DataError.Local.CONFLICT)
        }
    }

    override suspend fun deleteNote(ownerAccountId: String, id: NoteId) {
        notesDao.deleteNoteById(ownerAccountId, id)
    }

    override suspend fun deleteAllNotes(ownerAccountId: String) {
        notesDao.deleteAllNotes(ownerAccountId)
    }
}
