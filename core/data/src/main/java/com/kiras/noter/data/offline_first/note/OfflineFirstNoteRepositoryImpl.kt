package com.kiras.noter.data.offline_first.note

import com.kiras.noter.domain.accounts.repository.AuthActiveSessionStorage
import com.kiras.noter.domain.notes.model.Note
import com.kiras.noter.domain.notes.model.settings.NotesSortType
import com.kiras.noter.domain.notes.repository.NoteId
import com.kiras.noter.domain.notes.repository.NotesLocalDataSource
import com.kiras.noter.domain.notes.repository.NotesRepository
import com.kiras.noter.domain.util.DataError
import com.kiras.noter.domain.util.EmptyResult
import com.kiras.noter.domain.util.Result
import com.kiras.noter.domain.util.asEmptyDataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalCoroutinesApi::class)
class OfflineFirstNoteRepositoryImpl(
    private val localDataSource: NotesLocalDataSource,
    private val applicationScope: CoroutineScope,
    private val authActiveSessionStorage: AuthActiveSessionStorage
): NotesRepository {

    override suspend fun getNotes(query: String, sortType: NotesSortType): Flow<List<Note>> {
        val userId = authActiveSessionStorage.get()!!.userId
        return localDataSource.getNotes(
            ownerAccountId = userId,
            query = query,
            sortType = sortType
        )
    }

    override suspend fun getNotesByDay(
        dayStart: Long,
        dayEnd: Long,
        query: String,
        sortType: NotesSortType
    ): Flow<List<Note>> {
        val userId = authActiveSessionStorage.get()!!.userId
        return localDataSource.getNotesByDay(
            ownerAccountId = userId,
            dayStart = dayStart,
            dayEnd = dayEnd,
            query = query,
            sortType = sortType
        )
    }

    override fun getActiveUserNotesCount(): Flow<Int> {
        return authActiveSessionStorage.getAsFlow()
            .flatMapLatest { authInfo ->
                authInfo?.userId?.let { userId ->
                    localDataSource.getActiveUserNotesCount(userId)
                } ?: flowOf(0)
            }
    }

    override suspend fun getNote(id: NoteId): Note {
        return localDataSource.getNote(
            ownerAccountId = authActiveSessionStorage.get()!!.userId,
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
            ownerAccountId = authActiveSessionStorage.get()!!.userId,
            id = id
        )
    }

    override suspend fun deleteAllNotes() {
        localDataSource.deleteAllNotes(
            ownerAccountId = authActiveSessionStorage.get()!!.userId
        )
    }
}