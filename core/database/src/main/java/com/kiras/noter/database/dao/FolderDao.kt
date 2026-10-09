package com.kiras.noter.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.kiras.noter.database.entity.FolderEntity
import com.kiras.noter.database.entity.NoteFolderCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {

    @Query("SELECT * FROM folders WHERE ownerAccountId = :ownerAccountId ORDER BY createTime ASC")
    fun getFolders(ownerAccountId: String): Flow<List<FolderEntity>>

    @Upsert
    suspend fun upsertFolder(folder: FolderEntity)

    @Query("DELETE FROM folders WHERE ownerAccountId = :ownerAccountId AND id = :folderId")
    suspend fun deleteFolder(ownerAccountId: String, folderId: String)

    @Query("UPDATE folders SET name = :newName WHERE ownerAccountId = :ownerAccountId AND id = :folderId")
    suspend fun renameFolder(ownerAccountId: String, folderId: String, newName: String)

    @Upsert
    suspend fun insertNoteFolders(crossRefs: List<NoteFolderCrossRef>)

    @Query("DELETE FROM note_folders WHERE noteId = :noteId")
    suspend fun deleteNoteFoldersForNote(noteId: String)

    @Transaction
    suspend fun updateNoteFolders(noteId: String, folderIds: List<String>) {
        deleteNoteFoldersForNote(noteId)
        if (folderIds.isNotEmpty()) {
            insertNoteFolders(folderIds.map { NoteFolderCrossRef(noteId, it) })
        }
    }

    @Query("SELECT folderId FROM note_folders WHERE noteId = :noteId")
    suspend fun getFolderIdsForNote(noteId: String): List<String>

    @Query("SELECT folderId FROM note_folders WHERE noteId = :noteId")
    fun getFolderIdsForNoteAsFlow(noteId: String): Flow<List<String>>
}
