package com.kiras.noter.domain.use_case

import com.kiras.noter.domain.ClockProvider
import com.kiras.noter.domain.IdProvider
import com.kiras.noter.domain.accounts.repository.AuthActiveSessionStorage
import com.kiras.noter.domain.notes.model.Note
import com.kiras.noter.domain.notes.model.NoteColor
import com.kiras.noter.domain.notes.repository.NotesRepository

class NoteEditUseCase(
    private val notesRepository: NotesRepository,
    private val idProvider: IdProvider,
    private val clockProvider: ClockProvider,
    private val authActiveSessionStorage: AuthActiveSessionStorage
) {

    suspend fun upsertNote(note: Note) {
        notesRepository.upsertNote(note =
            note.copy(createTime = clockProvider.now())
        )
    }

    suspend fun getNote(id: String): Note {
        return notesRepository.getNote(id).copy(
            createTime = clockProvider.now()
        )
    }

    suspend fun createEmptyNote(): Note {
        return Note(
            id = idProvider.newId(),
            ownerAccountId = authActiveSessionStorage.get()!!.userId,
            title = "",
            content = "",
            color = NoteColor.DEFAULT,
            createTime = clockProvider.now()
        )
    }

    suspend fun deleteNote(id: String) {
        notesRepository.deleteNote(id)
    }
}