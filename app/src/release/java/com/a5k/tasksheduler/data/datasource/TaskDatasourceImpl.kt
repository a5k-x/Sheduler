package com.a5k.tasksheduler.data.datasource

import android.content.Context
import com.a5k.tasksheduler.data.model.TaskDto
import com.a5k.tasksheduler.util.getListTask
import com.a5k.tasksheduler.util.noAction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskDatasourceImpl @Inject constructor(
    private val context: Context
) : TaskDatasource {

    override suspend fun getAllTask(): List<TaskDto?> {
        return listOf()
    }

    override suspend fun getTask(id: Int): List<TaskDto?> {
        return listOf()
    }

    override suspend fun getTask(dateStart: Long, dateFinish: Long): List<TaskDto> {
        return getListTask(context).mapNotNull { task ->
            if (task.dateStart >= dateStart && task.dateFinish <= dateFinish) {
                task
            } else {
                null
            }
        }
    }

    override suspend fun saveTask(task: TaskDto) {
        noAction()
    }

    override suspend fun saveListTask(listTask: List<TaskDto>) {
        noAction()
    }

    override suspend fun deleteAllTask() {
        noAction()
    }

    override suspend fun deleteTask(id: Int) {
        noAction()
    }

    override suspend fun updateTask(task: TaskDto) {
        noAction()
    }
}