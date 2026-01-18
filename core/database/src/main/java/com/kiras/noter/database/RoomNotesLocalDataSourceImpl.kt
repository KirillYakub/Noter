package com.kiras.noter.database

import android.database.sqlite.SQLiteFullException
import com.kiras.noter.database.dao.NotesDao
import com.kiras.noter.database.mappers.toNote
import com.kiras.noter.database.mappers.toNoteEntity
import com.kiras.noter.domain.model.Note
import com.kiras.noter.domain.repository.NoteId
import com.kiras.noter.domain.repository.NotesLocalDataSource
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomNotesLocalDataSourceImpl(
    private val notesDao: NotesDao
): NotesLocalDataSource {

    override fun getNotes(): Flow<List<Note>> {
        return notesDao.getAllNotes().map { notes ->
            notes.map { it.toNote() }
        }
    }

    override suspend fun getNote(id: NoteId): Note {
        return notesDao.getNoteById(id).toNote()
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

    override suspend fun deleteNote(id: NoteId) {
        notesDao.deleteNoteById(id)
    }

    override suspend fun deleteAllNotes() {
        notesDao.deleteAllNotes()
    }
}