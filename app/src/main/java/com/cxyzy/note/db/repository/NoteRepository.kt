package com.cxyzy.note.db.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.cxyzy.note.db.bean.Note

interface NoteRepository {
    fun list(): LiveData<PagedList<Note>>

    suspend fun add(name: String)

    suspend fun update(note: Note)

    suspend fun del(id: Long)
}