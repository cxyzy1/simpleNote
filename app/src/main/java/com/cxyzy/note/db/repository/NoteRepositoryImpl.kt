package com.cxyzy.note.db.repository

import androidx.paging.Config
import androidx.paging.toLiveData
import com.cxyzy.note.db.bean.Note
import com.cxyzy.note.db.dao.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override fun list() = noteDao.getTaskList().toLiveData(Config(
            pageSize = 30,
            enablePlaceholders = true))

    override suspend fun add(name: String) {
        withContext(Dispatchers.IO) {
            val note = Note(0, name)
            noteDao.add(note)
        }
    }

    override suspend fun update(note: Note) {
        withContext(Dispatchers.IO) {
            note.updateDate = Date()
            noteDao.update(note)
        }
    }

    override suspend fun del(id: Long) {
        withContext(Dispatchers.IO) {
            noteDao.del(id)
        }
    }
}