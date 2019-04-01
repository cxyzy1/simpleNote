package com.cxyzy.note.viewmodels

import androidx.lifecycle.ViewModel
import com.cxyzy.note.db.bean.Note
import com.cxyzy.note.db.repository.NoteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel internal constructor(private val noteRepository: NoteRepository) : ViewModel() {
    val taskList = noteRepository.getTaskList()


    fun add(content: String) {
        GlobalScope.launch {
            noteRepository.add(content)
        }
    }

    fun update(note: Note) {
        GlobalScope.launch {
            noteRepository.update(note)
        }
    }

    fun del(id: Long) {
        GlobalScope.launch {
            noteRepository.del(id)
        }
    }
}