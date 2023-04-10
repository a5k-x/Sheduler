package com.a5k.tasksheduler.data.datasource

import com.a5k.tasksheduler.data.model.TaskDto

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