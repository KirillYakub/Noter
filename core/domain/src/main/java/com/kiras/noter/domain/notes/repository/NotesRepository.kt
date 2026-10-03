package com.kiras.noter.domain.notes.repository

import com.kiras.noter.domain.notes.model.Note
import com.kiras.noter.domain.notes.model.settings.NotesSortType
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun getNotes(
        query: String,
        sortType: NotesSortType,
    ): Flow<List<Note>>
    suspend fun getNotesByDay(
        dayStart: Long,
        dayEnd: Long,
        query: String,
        sortType: NotesSortType,
    ): Flow<List<Note>>

    fun getActiveUserNotesCount(): Flow<Int>
    suspend fun getNote(id: NoteId): Note
    suspend fun upsertNote(note: Note): EmptyResult<DataError>
    suspend fun deleteNote(id: NoteId)
    suspend fun deleteAllNotes()
}