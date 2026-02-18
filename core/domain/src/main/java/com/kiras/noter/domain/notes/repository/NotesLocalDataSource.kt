package com.kiras.noter.domain.notes.repository

import com.kiras.noter.domain.notes.model.Note
import com.kiras.noter.domain.notes.model.settings.NotesSortType
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.Result
import kotlinx.coroutines.flow.Flow

typealias NoteId = String

interface NotesLocalDataSource {
    fun getNotes(
        ownerAccountId: String,
        query: String,
        sortType: NotesSortType
    ): Flow<List<Note>>

    fun getNotesByDay(
        ownerAccountId: String,
        dayStart: Long,
        dayEnd: Long,
        query: String,
        sortType: NotesSortType
    ): Flow<List<Note>>

    suspend fun getNote(ownerAccountId: String, id: NoteId): Note
    suspend fun upsertNote(note: Note): Result<NoteId, DataError.Local>
    suspend fun deleteNote(ownerAccountId: String, id: NoteId)
    suspend fun deleteAllNotes(ownerAccountId: String)
}