package com.a5k.tasksheduler.data.datasource

import com.a5k.tasksheduler.data.database.AppDatabase
import com.a5k.tasksheduler.data.model.TaskDto
import com.a5k.tasksheduler.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskDatasourceImpl @Inject constructor(
    appDatabase: AppDatabase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : TaskDatasource {

    private val db = appDatabase.getTaskDao()

    override suspend fun getAllTask(): List<TaskDto?> {
        return withContext(ioDispatcher) {
            db.getAllTask()
        }
    }

    override suspend fun getTask(id: Int): List<TaskDto?> {
        return withContext(ioDispatcher) {
            db.getTask(id)
        }
    }

    override suspend fun getTask(dateStart: Long, dateFinish: Long): List<TaskDto?> {
        return withContext(ioDispatcher) {
            db.getTask(dateStart.toString(), dateFinish.toString())
        }
    }

    override suspend fun saveTask(task: TaskDto) {
        return withContext(ioDispatcher) {
            db.saveTask(task)
        }
    }

    override suspend fun saveListTask(listTask: List<TaskDto>) {
        return withContext(ioDispatcher) {
            db.saveListTask(listTask)
        }
    }

    override suspend fun deleteAllTask() {
        return withContext(ioDispatcher) {
            db.deleteAllTask()
        }
    }

    override suspend fun deleteTask(id: Int) {
        return withContext(ioDispatcher) {
            db.deleteTask(id)
        }
    }

    override suspend fun updateTask(task: TaskDto) {
        return withContext(ioDispatcher) {
            db.updateTask(task)
        }
    }
}