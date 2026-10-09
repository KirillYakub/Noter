package com.kiras.noter.database

import com.kiras.noter.database.dao.FolderDao
import com.kiras.noter.database.entity.FolderEntity
import com.kiras.noter.database.mappers.toFolder
import com.kiras.noter.domain.notes.model.folder.Folder
import com.kiras.noter.domain.notes.repository.FolderId
import com.kiras.noter.domain.notes.repository.FolderLocalDataSource
import com.kiras.noter.domain.notes.repository.NoteId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomFolderLocalDataSourceImpl(
    private val folderDao: FolderDao
): FolderLocalDataSource {

    override fun getFolders(ownerAccountId: String): Flow<List<Folder>> {
        return folderDao.getFolders(ownerAccountId)
            .map { list ->
                list.map { it.toFolder() }
            }
    }

    override suspend fun createFolder(ownerAccountId: String, name: String) {
        folderDao.upsertFolder(
            FolderEntity(
                ownerAccountId = ownerAccountId,
                name = name
            )
        )
    }

    override suspend fun renameFolder(ownerAccountId: String, folderId: FolderId, newName: String) {
        folderDao.renameFolder(ownerAccountId, folderId, newName)
    }

    override suspend fun deleteFolder(ownerAccountId: String, folderId: FolderId) {
        folderDao.deleteFolder(ownerAccountId, folderId)
    }

    override suspend fun getFolderIdsForNote(noteId: NoteId): List<FolderId> {
        return folderDao.getFolderIdsForNote(noteId)
    }

    override fun getFolderIdsForNoteAsFlow(noteId: NoteId): Flow<List<FolderId>> {
        return folderDao.getFolderIdsForNoteAsFlow(noteId)
    }
}
