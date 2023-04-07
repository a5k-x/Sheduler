package com.a5k.tasksheduler.presentation.repository

import com.a5k.tasksheduler.presentation.entity.Task

interface TaskRepository {

    suspend fun getAllTask(): List<Task>

    suspend fun getTask(id: Int): List<Task>

    suspend fun getTask(date: String): List<Task>

    suspend fun saveTask(task: Task)

    suspend fun saveListTask(listTask: List<Task>)

    suspend fun deleteAllTask()

    suspend fun deleteTask(id: Int)

    suspend fun updateTask(task: Task): Task
}