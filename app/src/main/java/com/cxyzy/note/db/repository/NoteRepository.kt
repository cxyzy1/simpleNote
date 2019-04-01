package com.cxyzy.note.db.repository

import androidx.paging.Config
import androidx.paging.toLiveData
import com.cxyzy.note.db.bean.Note
import com.cxyzy.note.db.dao.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class NoteRepository private constructor(private val noteDao: NoteDao) {

    fun getTaskList() = noteDao.getTaskList().toLiveData(Config(
            pageSize = 30,
            enablePlaceholders = true))

    suspend fun add(name: String) {
        withContext(Dispatchers.IO) {
            val note = Note(0, name, Date())
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