package com.cxyzy.note.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.cxyzy.note.DATABASE_NAME
import com.cxyzy.note.db.bean.Note
import com.cxyzy.note.db.converters.Converters
import com.cxyzy.note.db.dao.NoteDao
import com.cxyzy.note.db.test.DbTestDataInit


@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<DbTestDataInit>().build()
                            WorkManager.getInstance().enqueue(request)
                        }
                    })
                    .build()
        }
    }
}