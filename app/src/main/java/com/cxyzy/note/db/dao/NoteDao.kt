package com.cxyzy.note.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cxyzy.note.db.bean.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getTaskList(): DataSource.Factory<Int, Note>

    @Insert
    fun add(note: Note)

    @Insert
    fun add(notes: List<Note>)

    @Delete
    fun del(note: Note)
}