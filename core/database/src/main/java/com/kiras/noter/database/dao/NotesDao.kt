package com.kiras.noter.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kiras.noter.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Upsert
    suspend fun upsertNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes WHERE ownerAccountId = :ownerAccountId")
    fun getAllNotes(ownerAccountId: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes 
        WHERE ownerAccountId = :ownerAccountId
        AND createTime BETWEEN :start AND :end
    """)
    fun getNotesByDay(ownerAccountId: String, start: Long, end: Long): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE ownerAccountId = :ownerAccountId AND id = :id LIMIT 1")
    suspend fun getNoteById(ownerAccountId: String, id: String): NoteEntity

    @Query("DELETE FROM notes WHERE ownerAccountId = :ownerAccountId AND id = :id")
    suspend fun deleteNoteById(ownerAccountId: String, id: String)

    @Query("DELETE FROM notes WHERE ownerAccountId = :ownerAccountId")
    suspend fun deleteAllNotes(ownerAccountId: String)
}