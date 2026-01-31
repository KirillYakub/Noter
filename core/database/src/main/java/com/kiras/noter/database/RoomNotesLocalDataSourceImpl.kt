package com.kiras.noter.database

import android.database.sqlite.SQLiteFullException
import com.kiras.noter.database.dao.NotesDao
import com.kiras.noter.database.mappers.toNote
import com.kiras.noter.database.mappers.toNoteEntity
import com.kiras.noter.domain.notes.model.Note
import com.kiras.noter.domain.notes.repository.NoteId
import com.kiras.noter.domain.notes.repository.NotesLocalDataSource
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomNotesLocalDataSourceImpl(
    private val notesDao: NotesDao
): NotesLocalDataSource {

    override fun getNotes(ownerAccountId: String): Flow<List<Note>> {
        return notesDao.getAllNotes(ownerAccountId).map { notes ->
            notes.map { it.toNote() }
        }
    }

    override fun getNotesByDay(
        ownerAccountId: String,
        dayStart: Long,
        dayEnd: Long,
    ): Flow<List<Note>> {
        return notesDao.getNotesByDay(ownerAccountId, dayStart, dayEnd).map { notes ->
            notes.map { it.toNote() }
        }
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