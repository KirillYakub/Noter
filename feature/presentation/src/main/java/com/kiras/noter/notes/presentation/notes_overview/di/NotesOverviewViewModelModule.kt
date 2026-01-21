package com.kiras.noter.notes.presentation.notes_overview.di

import com.kiras.noter.notes.presentation.notes_overview.NotesOverviewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val notesOverviewViewModelModule = module {
    viewModelOf(::NotesOverviewViewModel)
}