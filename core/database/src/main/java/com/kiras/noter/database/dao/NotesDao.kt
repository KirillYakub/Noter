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

    @Query("SELECT COUNT(*) FROM notes WHERE ownerAccountId = :ownerAccountId")
    fun getNotesCount(ownerAccountId: String): Flow<Int>

    @Query("SELECT * FROM notes WHERE ownerAccountId = :ownerAccountId AND id = :id LIMIT 1")
    suspend fun getNoteById(ownerAccountId: String, id: String): NoteEntity

    @Query("DELETE FROM notes WHERE ownerAccountId = :ownerAccountId AND id = :id")
    suspend fun deleteNoteById(ownerAccountId: String, id: String)

    @Query("DELETE FROM notes WHERE ownerAccountId = :ownerAccountId")
    suspend fun deleteAllNotes(ownerAccountId: String)

    @Query("""
        SELECT notes.* FROM notes
        INNER JOIN note_folders ON notes.id = note_folders.noteId
        WHERE notes.ownerAccountId = :ownerAccountId AND note_folders.folderId = :folderId
        ORDER BY notes.createTime DESC
    """)
    fun getNotesByFolderByDate(ownerAccountId: String, folderId: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT notes.* FROM notes
        INNER JOIN note_folders ON notes.id = note_folders.noteId
        WHERE notes.ownerAccountId = :ownerAccountId AND note_folders.folderId = :folderId
        ORDER BY LOWER(COALESCE(notes.title, '') || ' ' || COALESCE(notes.content, '')) ASC
    """)
    fun getNotesByFolderAlphabetically(ownerAccountId: String, folderId: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT notes.* FROM notes
        INNER JOIN note_folders ON notes.id = note_folders.noteId
        WHERE notes.ownerAccountId = :ownerAccountId AND note_folders.folderId = :folderId
          AND (:q = '' OR notes.title LIKE '%' || :q || '%' OR notes.content LIKE '%' || :q || '%')
        ORDER BY notes.createTime DESC
    """)
    fun searchNotesByFolderByDate(ownerAccountId: String, folderId: String, q: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT notes.* FROM notes
        INNER JOIN note_folders ON notes.id = note_folders.noteId
        WHERE notes.ownerAccountId = :ownerAccountId AND note_folders.folderId = :folderId
          AND (:q = '' OR notes.title LIKE '%' || :q || '%' OR notes.content LIKE '%' || :q || '%')
        ORDER BY LOWER(COALESCE(notes.title, '') || ' ' || COALESCE(notes.content, '')) ASC
    """)
    fun searchNotesByFolderAlphabetically(ownerAccountId: String, folderId: String, q: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId AND isImportant = 1
        ORDER BY createTime DESC
    """)
    fun getImportantNotesByDate(ownerAccountId: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId AND isImportant = 1
        ORDER BY LOWER(COALESCE(title, '') || ' ' || COALESCE(content, '')) ASC
    """)
    fun getImportantNotesAlphabetically(ownerAccountId: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId AND isImportant = 1
          AND (:q = '' OR title LIKE '%' || :q || '%' OR content LIKE '%' || :q || '%')
        ORDER BY createTime DESC
    """)
    fun searchImportantNotesByDate(ownerAccountId: String, q: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId AND isImportant = 1
          AND (:q = '' OR title LIKE '%' || :q || '%' OR content LIKE '%' || :q || '%')
        ORDER BY LOWER(COALESCE(title, '') || ' ' || COALESCE(content, '')) ASC
    """)
    fun searchImportantNotesAlphabetically(ownerAccountId: String, q: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT notes.* FROM notes
        INNER JOIN note_folders ON notes.id = note_folders.noteId
        WHERE notes.ownerAccountId = :ownerAccountId AND note_folders.folderId = :folderId
          AND notes.createTime BETWEEN :start AND :end
        ORDER BY notes.createTime DESC
    """)
    fun getNotesByDayAndFolderByDate(ownerAccountId: String, folderId: String, start: Long, end: Long): Flow<List<NoteEntity>>

    @Query("""
        SELECT notes.* FROM notes
        INNER JOIN note_folders ON notes.id = note_folders.noteId
        WHERE notes.ownerAccountId = :ownerAccountId AND note_folders.folderId = :folderId
          AND notes.createTime BETWEEN :start AND :end
        ORDER BY LOWER(COALESCE(notes.title, '') || ' ' || COALESCE(notes.content, '')) ASC
    """)
    fun getNotesByDayAndFolderAlphabetically(ownerAccountId: String, folderId: String, start: Long, end: Long): Flow<List<NoteEntity>>

    @Query("""
        SELECT notes.* FROM notes
        INNER JOIN note_folders ON notes.id = note_folders.noteId
        WHERE notes.ownerAccountId = :ownerAccountId AND note_folders.folderId = :folderId
          AND notes.createTime BETWEEN :start AND :end
          AND (:q = '' OR notes.title LIKE '%' || :q || '%' OR notes.content LIKE '%' || :q || '%')
        ORDER BY notes.createTime DESC
    """)
    fun searchNotesByDayAndFolderByDate(ownerAccountId: String, folderId: String, start: Long, end: Long, q: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT notes.* FROM notes
        INNER JOIN note_folders ON notes.id = note_folders.noteId
        WHERE notes.ownerAccountId = :ownerAccountId AND note_folders.folderId = :folderId
          AND notes.createTime BETWEEN :start AND :end
          AND (:q = '' OR notes.title LIKE '%' || :q || '%' OR notes.content LIKE '%' || :q || '%')
        ORDER BY LOWER(COALESCE(notes.title, '') || ' ' || COALESCE(notes.content, '')) ASC
    """)
    fun searchNotesByDayAndFolderAlphabetically(ownerAccountId: String, folderId: String, start: Long, end: Long, q: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId AND isImportant = 1
          AND createTime BETWEEN :start AND :end
        ORDER BY createTime DESC
    """)
    fun getImportantNotesByDayByDate(ownerAccountId: String, start: Long, end: Long): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId AND isImportant = 1
          AND createTime BETWEEN :start AND :end
        ORDER BY LOWER(COALESCE(title, '') || ' ' || COALESCE(content, '')) ASC
    """)
    fun getImportantNotesByDayAlphabetically(ownerAccountId: String, start: Long, end: Long): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId AND isImportant = 1
          AND createTime BETWEEN :start AND :end
          AND (:q = '' OR title LIKE '%' || :q || '%' OR content LIKE '%' || :q || '%')
        ORDER BY createTime DESC
    """)
    fun searchImportantNotesByDayByDate(ownerAccountId: String, start: Long, end: Long, q: String): Flow<List<NoteEntity>>

    @Query("""
        SELECT * FROM notes
        WHERE ownerAccountId = :ownerAccountId AND isImportant = 1
          AND createTime BETWEEN :start AND :end
          AND (:q = '' OR title LIKE '%' || :q || '%' OR content LIKE '%' || :q || '%')
        ORDER BY LOWER(COALESCE(title, '') || ' ' || COALESCE(content, '')) ASC
    """)
    fun searchImportantNotesByDayAlphabetically(ownerAccountId: String, start: Long, end: Long, q: String): Flow<List<NoteEntity>>
}
