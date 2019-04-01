package com.cxyzy.note.db.test

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cxyzy.note.db.AppDatabase
import com.cxyzy.note.db.bean.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

class DbTestDataInit(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override val coroutineContext = Dispatchers.IO

    override suspend fun doWork(): Result = coroutineScope {

        val database = AppDatabase.getInstance(applicationContext)

//        val mList = mutableListOf<Note>()
//        (1..10).forEach {
//            mList.add(Note(it, "note$it"))
//        }
//        database.taskDao().add(mList)
        database.taskDao().add(Note(0, "sample Note"))
        Result.success()
    }
}