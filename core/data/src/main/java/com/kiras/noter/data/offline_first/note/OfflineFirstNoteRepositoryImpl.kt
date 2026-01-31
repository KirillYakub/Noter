package com.kiras.noter.data.offline_first.note

import com.kiras.noter.domain.SessionStorage
import com.kiras.noter.domain.notes.model.Note
import com.kiras.noter.domain.notes.repository.NoteId
import com.kiras.noter.domain.notes.repository.NotesLocalDataSource
import com.kiras.noter.domain.notes.repository.NotesRepository
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.EmptyResult
import com.kiras.noter.domain.util.Result
import com.kiras.noter.domain.util.asEmptyDataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class OfflineFirstNoteRepositoryImpl(
    private val localDataSource: NotesLocalDataSource,
    private val applicationScope: CoroutineScope,
    private val sessionStorage: SessionStorage
): NotesRepository {

    override suspend fun getNotes(): Flow<List<Note>> {
        return localDataSource.getNotes(
            ownerAccountId = sessionStorage.get()!!.userId
        )
    }

    override suspend fun getNotesByDay(
        dayStart: Long,
        dayEnd: Long,
    ): Flow<List<Note>> {
        return localDataSource.getNotesByDay(
            ownerAccountId = sessionStorage.get()!!.userId,
            dayStart = dayStart,
            dayEnd = dayEnd
        )
    }

    override suspend fun getNote(id: NoteId): Note {
        return localDataSource.getNote(
            ownerAccountId = sessionStorage.get()!!.userId,
            id = id
        )
    }

    override suspend fun upsertNote(note: Note): EmptyResult<DataError> {
        val result = localDataSource.upsertNote(note)
        if(result !is Result.Success) {
            return result.asEmptyDataResult()
        }
        return Result.Success(Unit)
    }

    override suspend fun deleteNote(id: NoteId) {
        localDataSource.deleteNote(
            ownerAccountId = sessionStorage.get()!!.userId,
            id = id
        )
    }

    override suspend fun deleteAllNotes() {
        localDataSource.deleteAllNotes(
            ownerAccountId = sessionStorage.get()!!.userId
        )
    }
}