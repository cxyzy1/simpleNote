package com.cxyzy.note

import com.cxyzy.note.db.AppDatabase
import com.cxyzy.note.db.repository.NoteRepository

object InjectionUtil {
    private val dbInstance = AppDatabase.getInstance(App.context)

    fun getTaskRepository(): NoteRepository = NoteRepository.getInstance(dbInstance.taskDao())

}