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

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId
        ORDER BY createTime DESC
    """)
    fun getAllNotesByDate(ownerAccountId: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId
        ORDER BY LOWER(
            COALESCE(title, '') || ' ' || COALESCE(content, '')
        ) ASC
    """)
    fun getAllNotesAlphabetically(ownerAccountId: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId
          AND (:q = '' OR
               title LIKE '%' || :q || '%' OR
               content LIKE '%' || :q || '%')
        ORDER BY createTime DESC
    """)
    fun searchNotesByDate(ownerAccountId: String, q: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId
          AND (:q = '' OR
               title LIKE '%' || :q || '%' OR
               content LIKE '%' || :q || '%')
        ORDER BY LOWER(
            COALESCE(title, '') || ' ' || COALESCE(content, '')
        ) ASC
    """)
    fun searchNotesAlphabetically(ownerAccountId: String, q: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId
          AND createTime BETWEEN :start AND :end
        ORDER BY createTime DESC
    """)
    fun getNotesByDayByDate(
        ownerAccountId: String,
        start: Long,
        end: Long
    ): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId
          AND createTime BETWEEN :start AND :end
        ORDER BY LOWER(
            COALESCE(title, '') || ' ' || COALESCE(content, '')
        ) ASC
    """)
    fun getNotesByDayAlphabetically(
        ownerAccountId: String,
        start: Long,
        end: Long
    ): Flow<List<NoteEntity>>

    @Query(
        """
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId
          AND createTime BETWEEN :start AND :end
          AND (:q = '' OR
               title LIKE '%' || :q || '%' OR
               content LIKE '%' || :q || '%')
        ORDER BY createTime DESC
        """
    )
    fun searchNotesByDayByDate(
        ownerAccountId: String,
        start: Long,
        end: Long,
        q: String
    ): Flow<List<NoteEntity>>

    @Query(
        """
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId
          AND createTime BETWEEN :start AND :end
          AND (:query = '' OR
               title LIKE '%' || :query || '%' OR
               content LIKE '%' || :query || '%')
        ORDER BY LOWER(
            COALESCE(title, '') || ' ' || COALESCE(content, '')
        ) ASC
    """
    )
    fun searchNotesByDayAlphabetically(
        ownerAccountId: String,
        start: Long,
        end: Long,
        query: String
    ): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE ownerAccountId = :ownerAccountId AND id = :id LIMIT 1")
    suspend fun getNoteById(ownerAccountId: String, id: String): NoteEntity

    @Query("DELETE FROM notes WHERE ownerAccountId = :ownerAccountId AND id = :id")
    suspend fun deleteNoteById(ownerAccountId: String, id: String)

    @Query("DELETE FROM notes WHERE ownerAccountId = :ownerAccountId")
    suspend fun deleteAllNotes(ownerAccountId: String)
}
