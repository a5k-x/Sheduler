package com.a5k.tasksheduler.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.a5k.tasksheduler.data.model.TaskDto

@Database(
    entities = [
        TaskDto::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

    companion object {

        private var instance: AppDatabase? = null
        private const val NAME_DB = "task_db"

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(applicationContext: Context) =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, NAME_DB)
                .build()
    }
}

