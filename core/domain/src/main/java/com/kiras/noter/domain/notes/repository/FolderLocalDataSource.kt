package com.kiras.noter.domain.notes.repository

import com.kiras.noter.domain.notes.model.folder.Folder
import kotlinx.coroutines.flow.Flow

interface FolderLocalDataSource {
    fun getFolders(ownerAccountId: String): Flow<List<Folder>>
    suspend fun createFolder(ownerAccountId: String, name: String)
    suspend fun renameFolder(ownerAccountId: String, folderId: FolderId, newName: String)
    suspend fun deleteFolder(ownerAccountId: String, folderId: FolderId)
    suspend fun getFolderIdsForNote(noteId: NoteId): List<FolderId>
    fun getFolderIdsForNoteAsFlow(noteId: NoteId): Flow<List<FolderId>>
}
