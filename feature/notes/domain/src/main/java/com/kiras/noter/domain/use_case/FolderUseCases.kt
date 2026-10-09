package com.kiras.noter.domain.use_case

import com.kiras.noter.domain.notes.model.folder.Folder
import com.kiras.noter.domain.notes.repository.FolderRepository
import kotlinx.coroutines.flow.Flow

class FolderUseCases(
    private val folderRepository: FolderRepository
) {
    fun getFolders(): Flow<List<Folder>> {
        return folderRepository.getFolders()
    }

    suspend fun createFolder(name: String) {
        if (name.isNotBlank()) {
            folderRepository.createFolder(name.trim())
        }
    }

    suspend fun renameFolder(folderId: String, newName: String) {
        if (newName.isNotBlank()) {
            folderRepository.renameFolder(folderId, newName.trim())
        }
    }

    suspend fun deleteFolder(folderId: String) {
        folderRepository.deleteFolder(folderId)
    }

    fun getFolderIdsForNoteAsFlow(noteId: String): Flow<List<String>> {
        return folderRepository.getFolderIdsForNoteAsFlow(noteId)
    }
}
