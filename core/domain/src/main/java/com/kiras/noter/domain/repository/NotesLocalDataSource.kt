package com.kiras.noter.domain.repository

import com.kiras.noter.domain.model.Note
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.Result
import kotlinx.coroutines.flow.Flow

typealias NoteId = String

interface NotesLocalDataSource {
    fun getNotes(): Flow<List<Note>>
    suspend fun getNote(id: NoteId): Note
    suspend fun upsertNote(note: Note): Result<NoteId, DataError.Local>
    suspend fun deleteNote(id: NoteId)
    suspend fun deleteAllNotes()
}