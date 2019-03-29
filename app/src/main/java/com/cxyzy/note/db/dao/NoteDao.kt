package com.cxyzy.note.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.cxyzy.note.db.bean.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getTaskList(): DataSource.Factory<Int, Note>

    @Insert
    fun add(note: Note)

    @Insert
    fun add(notes: List<Note>)

    @Update
    fun update(note: Note)

    @Delete
    fun del(note: Note)

}