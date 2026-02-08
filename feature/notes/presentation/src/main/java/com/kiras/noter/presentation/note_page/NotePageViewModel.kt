package com.kiras.noter.presentation.note_page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.kiras.noter.domain.use_case.NoteEditUseCase
import com.kiras.noter.mapper.toNote
import com.kiras.noter.mapper.toNoteUi
import com.kiras.noter.presentation.note_page.model.NoteDraft
import com.kiras.noter.presentation.util.NotePage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class NotePageViewModel(
    private val noteEditUseCase: NoteEditUseCase,
    private val applicationScope: CoroutineScope,
    saveStateHandle: SavedStateHandle
) : ViewModel() {

    var notePageState by mutableStateOf(NotePageState())
        private set

    private var noteDraft = NoteDraft()

    init {
        val args = saveStateHandle.toRoute<NotePage>()
        viewModelScope.launch {
            val note = args.id?.let { id -> noteEditUseCase.getNote(id) }
                ?: noteEditUseCase.createEmptyNote()
            noteDraft = NoteDraft(
                noteCreateTime = note.createTime,
                ownerAccountId = note.ownerAccountId
            )
            notePageState = with(notePageState) {
                val noteUi = note.toNoteUi()
                copy(
                    noteUi = noteUi.copy(
                        id = noteUi.id,
                        title = noteUi.title,
                        content = noteUi.content,
                        color = noteUi.color,
                        createTime = noteUi.createTime
                    )
                )
            }
        }

        snapshotFlow { notePageState }
            .debounce(300)
            .distinctUntilChanged()
            .onEach { notePageStateLatest ->
                when {
                    noteDraft.noteCreateTime == null -> return@onEach
                    notePageStateLatest.isNoteBlank() -> {
                        noteEditUseCase.deleteNote(id = notePageStateLatest.noteUi.id)
                    }
                    else -> {
                        noteEditUseCase.upsertNote(note =
                            notePageStateLatest.noteUi.toNote(
                                ownerAccountId = noteDraft.ownerAccountId!!,
                                createTimeAsZoneDateTime = noteDraft.noteCreateTime!!
                            )
                        )
                    }
                }
            }
            .launchIn(applicationScope)
    }

    fun onAction(action: NotePageActions) {
        when(action) {
            is NotePageActions.OnColorChange -> {
                notePageState = with(notePageState) {
                    copy(
                        noteUi = noteUi.copy(
                            color = action.color
                        )
                    )
                }
            }
            is NotePageActions.OnContentChange -> {
                notePageState = with(notePageState) {
                    copy(
                        noteUi = noteUi.copy(
                            content = action.content
                        )
                    )
                }
            }
            is NotePageActions.OnTitleChange -> {
                notePageState = with(notePageState) {
                    copy(
                        noteUi = noteUi.copy(
                            title = action.title
                        )
                    )
                }
            }
            is NotePageActions.OnAlignChange -> {
                notePageState = with(notePageState) {
                    copy(
                        alignment = action.align
                    )
                }
            }
            else -> Unit
        }
    }
}