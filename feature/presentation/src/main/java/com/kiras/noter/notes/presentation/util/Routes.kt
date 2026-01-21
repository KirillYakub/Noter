package com.kiras.noter.notes.presentation.util

import kotlinx.serialization.Serializable

@Serializable
data object NotesOverview

@Serializable
data class NotePage(val id: String? = null)