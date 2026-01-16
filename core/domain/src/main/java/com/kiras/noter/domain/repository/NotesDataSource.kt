package com.kiras.noter.domain.repository

interface NotesDataSource {
    suspend fun getNotes(): List<Note>
    suspend fun getNoteById(id: String): Note?
}