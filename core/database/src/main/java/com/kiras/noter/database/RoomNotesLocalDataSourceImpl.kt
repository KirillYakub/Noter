package com.kiras.noter.database

import android.database.sqlite.SQLiteFullException
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
    private val notesDao: NotesDao
): NotesLocalDataSource {

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
        }.map { list -> list.map { it.toNote() } }
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
            list.map { it.toNote() }
        }
    }

    override fun getActiveUserNotesCount(ownerAccountId: String): Flow<Int> {
        return notesDao.getNotesCount(ownerAccountId)
    }

    override suspend fun getNote(ownerAccountId: String, id: NoteId): Note {
        return notesDao.getNoteById(ownerAccountId, id).toNote()
    }

    override suspend fun upsertNote(note: Note): Result<NoteId, DataError.Local> {
        return try {
            val noteAsEntity = note.toNoteEntity()
            notesDao.upsertNote(noteAsEntity)
            Result.Success(noteAsEntity.id)
        } catch (_: SQLiteFullException) {
            Result.Error(DataError.Local.DISC_FULL)
        }
    }

    override suspend fun deleteNote(ownerAccountId: String, id: NoteId) {
        notesDao.deleteNoteById(ownerAccountId, id)
    }

    override suspend fun deleteAllNotes(ownerAccountId: String) {
        notesDao.deleteAllNotes(ownerAccountId)
    }
}