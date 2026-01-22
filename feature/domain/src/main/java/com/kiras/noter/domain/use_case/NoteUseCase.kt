package com.kiras.noter.domain.use_case

import com.kiras.noter.domain.model.Note
import com.kiras.noter.domain.model.NoteColor
import com.kiras.noter.domain.repository.ClockProvider
import com.kiras.noter.domain.repository.IdProvider
import com.kiras.noter.domain.repository.NotesRepository

class NoteUseCase(
    private val repository: NotesRepository,
    private val idProvider: IdProvider,
    private val clockProvider: ClockProvider
) {
    suspend fun upsertNote(note: Note) {
        repository.upsertNote(note =
            if(note.id == null) note.copy(createTime = clockProvider.now())
            else note
        )
    }

    suspend fun getNote(id: String): Note {
        return repository.getNote(id).copy(
            createTime = clockProvider.now()
        )
    }

    fun createEmptyNote(): Note {
        return Note(
            id = idProvider.newId(),
            title = "",
            content = "",
            color = NoteColor.DEFAULT,
            createTime = clockProvider.now()
        )
    }

    suspend fun deleteNote(id: String) {
        repository.deleteNote(id)
    }
}