package com.kiras.noter.notes.presentation.note_page.di

import com.kiras.noter.notes.presentation.note_page.NotePageViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val notePageViewModelModule = module {
    viewModelOf(::NotePageViewModel)
}