package com.cxyzy.note.db.repository

import androidx.paging.Config
import androidx.paging.toLiveData
import com.cxyzy.note.App
import com.cxyzy.note.db.AppDatabase
import com.cxyzy.note.db.bean.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

object NoteRepository {
    private val noteDao = AppDatabase.getInstance(App.context).noteDao()
    fun getNoteList() = noteDao.getTaskList().toLiveData(Config(
            pageSize = 30,
            enablePlaceholders = true))

    suspend fun add(name: String) {
        withContext(Dispatchers.IO) {
            val note = Note(0, name)
            noteDao.add(note)
        }
    }

    suspend fun update(note: Note) {
        withContext(Dispatchers.IO) {
            note.updateDate = Date()
            noteDao.update(note)
        }
    }

    suspend fun del(id: Long) {
        withContext(Dispatchers.IO) {
            noteDao.del(id)
        }
    }
}