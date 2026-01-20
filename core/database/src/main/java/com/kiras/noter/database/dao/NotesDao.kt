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

    @Query("SELECT * FROM noteentity")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM noteentity WHERE createTime BETWEEN :start AND :end")
    fun getNotesByDay(start: Long, end: Long): Flow<List<NoteEntity>>

    @Query("SELECT * FROM noteentity WHERE id = :id")
    suspend fun getNoteById(id: String): NoteEntity

    @Query("DELETE FROM noteentity WHERE id = :id")
    suspend fun deleteNoteById(id: String)

    @Query("DELETE FROM noteentity")
    suspend fun deleteAllNotes()
}