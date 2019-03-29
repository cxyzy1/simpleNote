package com.cxyzy.note.viewmodels

import androidx.lifecycle.ViewModel
import com.cxyzy.note.db.bean.Note
import com.cxyzy.note.db.repository.NoteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditNoteViewModel internal constructor(private val noteRepository: NoteRepository) : ViewModel() {
    fun update(note: Note) {
        GlobalScope.launch {
            noteRepository.update(note)
        }
    }
}