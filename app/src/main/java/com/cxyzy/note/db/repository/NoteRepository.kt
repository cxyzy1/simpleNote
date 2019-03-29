package com.cxyzy.note.db.repository

import androidx.paging.Config
import androidx.paging.toLiveData
import com.cxyzy.note.db.bean.Note
import com.cxyzy.note.db.dao.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository private constructor(private val noteDao: NoteDao) {

    fun getTaskList() = noteDao.getTaskList().toLiveData(Config(
            pageSize = 30,
            enablePlaceholders = true))

    suspend fun add(name: String) {
        withContext(Dispatchers.IO) {
            val task = Note(0, name)
            noteDao.add(task)
        }
    }

    suspend fun delTask(id: Int) {
        withContext(Dispatchers.IO) {
            val task = Note(id, "")
            noteDao.del(task)
        }
    }

    companion object {
        @Volatile
        private var instance: NoteRepository? = null

        fun getInstance(noteDao: NoteDao) =
                instance ?: synchronized(this) {
                    instance
                            ?: NoteRepository(noteDao).also { instance = it }
                }
    }
}