package com.kiras.noter.domain.repository

import com.kiras.noter.domain.model.Note
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNotes(): Flow<List<Note>>
    fun getNotesByDay(dayStart: Long, dayEnd: Long): Flow<List<Note>>
    suspend fun fetchNotes(): EmptyResult<DataError>
    suspend fun upsertRun(note: Note): EmptyResult<DataError>
    suspend fun deleteNote(id: NoteId)
    suspend fun deleteAllNotes()
}