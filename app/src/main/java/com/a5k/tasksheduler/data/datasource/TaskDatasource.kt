package com.a5k.tasksheduler.data.datasource

import androidx.lifecycle.LiveData
import com.a5k.tasksheduler.data.model.TaskDto
import kotlinx.coroutines.flow.Flow

interface TaskDatasource {

    suspend fun getAllTask(): List<TaskDto?>

    suspend fun getTask(id: Int): List<TaskDto?>

    suspend fun getTask(dateStart: Long, dateFinish: Long): List<TaskDto>

    suspend fun saveTask(task: TaskDto)

    suspend fun saveListTask(listTask: List<TaskDto>)

    suspend fun deleteAllTask()

    suspend fun deleteTask(id: Int)

    suspend fun updateTask(task: TaskDto)
}