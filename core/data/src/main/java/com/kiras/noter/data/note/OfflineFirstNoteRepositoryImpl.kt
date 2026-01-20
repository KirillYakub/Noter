package com.kiras.noter.data.note

import com.kiras.noter.domain.model.Note
import com.kiras.noter.domain.repository.NoteId
import com.kiras.noter.domain.repository.NotesLocalDataSource
import com.kiras.noter.domain.repository.NotesRepository
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.EmptyResult
import com.kiras.noter.domain.util.asEmptyDataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class OfflineFirstNoteRepositoryImpl(
    private val localDataSource: NotesLocalDataSource,
    private val applicationScope: CoroutineScope
): NotesRepository {

    override fun getNotes(): Flow<List<Note>> {
        return localDataSource.getNotes()
    }

    override fun getNotesByDay(
        dayStart: Long,
        dayEnd: Long,
    ): Flow<List<Note>> {
        return localDataSource.getNotesByDay(dayStart, dayEnd)
    }

    override suspend fun fetchNotes(): EmptyResult<DataError> {
        TODO("Not yet implemented")
    }

    override suspend fun upsertRun(note: Note): EmptyResult<DataError> {
        val result = localDataSource.upsertNote(note)

        //Implement logic to push data on server

        return result.asEmptyDataResult()
    }

    override suspend fun deleteNote(id: NoteId) {
        localDataSource.deleteNote(id)

        //Implement logic to delete data from server
    }

    override suspend fun deleteAllNotes() {
        localDataSource.deleteAllNotes()
    }
}