package com.a5k.tasksheduler.presentation.repository

import com.a5k.tasksheduler.presentation.entity.Task

interface TaskRepository {

    fun getAllTask(): List<Task>

    fun getTask(id: Int): List<Task>

    fun getTask(date: String): List<Task>

    fun saveTask(task: Task)

    fun saveListTask(task: Task)

    fun deleteAllTask()

    fun deleteTask(id: Int)

    fun updateTask(task: Task): Task
}