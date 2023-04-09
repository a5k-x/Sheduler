package com.a5k.tasksheduler.domain.repository

import com.a5k.tasksheduler.domain.entity.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun getAllTask(): List<Task>

    suspend fun getTask(id: Int): List<Task>

    suspend fun getTask(date: String): List<Task>

    suspend fun saveTask(task: Task)

    suspend fun saveListTask(listTask: List<Task>)

    suspend fun deleteAllTask()

    suspend fun deleteTask(id: Int)

    suspend fun updateTask(task: Task)
}