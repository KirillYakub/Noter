package com.kiras.noter.data.offline_first.folder

import com.kiras.noter.domain.accounts.repository.AuthActiveSessionStorage
import com.kiras.noter.domain.notes.model.folder.Folder
import com.kiras.noter.domain.notes.repository.FolderId
import com.kiras.noter.domain.notes.repository.FolderLocalDataSource
import com.kiras.noter.domain.notes.repository.FolderRepository
import com.kiras.noter.domain.notes.repository.NoteId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalCoroutinesApi::class)
class OfflineFirstFolderRepositoryImpl(
    private val localDataSource: FolderLocalDataSource,
    private val applicationScope: CoroutineScope,
    private val authActiveSessionStorage: AuthActiveSessionStorage
) : FolderRepository {

    override fun getFolders(): Flow<List<Folder>> {
        return authActiveSessionStorage.getAsFlow()
            .flatMapLatest { authInfo ->
                authInfo?.userId?.let { userId ->
                    localDataSource.getFolders(userId)
                } ?: flowOf(emptyList())
            }
    }

    override suspend fun createFolder(name: String) {
        val userId = authActiveSessionStorage.get()!!.userId
        localDataSource.createFolder(userId, name)
    }

    override suspend fun renameFolder(folderId: FolderId, newName: String) {
        val userId = authActiveSessionStorage.get()!!.userId
        localDataSource.renameFolder(userId, folderId, newName)
    }

    override suspend fun deleteFolder(folderId: FolderId) {
        val userId = authActiveSessionStorage.get()!!.userId
        localDataSource.deleteFolder(userId, folderId)
    }

    override suspend fun getFolderIdsForNote(noteId: NoteId): List<FolderId> {
        return localDataSource.getFolderIdsForNote(noteId)
    }

    override fun getFolderIdsForNoteAsFlow(noteId: NoteId): Flow<List<FolderId>> {
        return localDataSource.getFolderIdsForNoteAsFlow(noteId)
    }
}
