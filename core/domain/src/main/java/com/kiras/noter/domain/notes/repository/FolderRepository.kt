package com.kiras.noter.domain.notes.repository

import com.kiras.noter.domain.notes.model.folder.Folder
import kotlinx.coroutines.flow.Flow

typealias FolderId = String

interface FolderRepository {
    fun getFolders(): Flow<List<Folder>>
    suspend fun createFolder(name: String)
    suspend fun renameFolder(folderId: FolderId, newName: String)
    suspend fun deleteFolder(folderId: FolderId)
    suspend fun getFolderIdsForNote(noteId: NoteId): List<FolderId>
    fun getFolderIdsForNoteAsFlow(noteId: NoteId): Flow<List<FolderId>>
}
