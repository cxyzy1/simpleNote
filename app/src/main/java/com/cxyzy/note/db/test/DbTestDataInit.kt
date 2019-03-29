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

        val mList = mutableListOf<Note>()

        (1..1000).forEach {
            mList.add(Note(it, "note$it"))
        }
        database.taskDao().add(mList)
        Result.success()
    }
}