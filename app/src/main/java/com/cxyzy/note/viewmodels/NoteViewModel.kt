package com.cxyzy.note.viewmodels

import androidx.lifecycle.ViewModel
import com.cxyzy.note.db.repository.NoteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel internal constructor(private val noteRepository: NoteRepository) : ViewModel() {
    val taskList = noteRepository.getTaskList()

    fun delTask(id: Int) {
        GlobalScope.launch {
            noteRepository.delTask(id)
        }
    }
}