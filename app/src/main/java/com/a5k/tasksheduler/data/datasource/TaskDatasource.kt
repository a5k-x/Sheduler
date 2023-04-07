package com.a5k.tasksheduler.data.datasource

import com.a5k.tasksheduler.data.model.TaskDao

interface TaskDatasource {

    fun getAllTask(): List<TaskDao>

    fun getTask(id: Int): List<TaskDao>

    fun saveTask(task: TaskDao)

    fun saveList(task: TaskDao)

    fun deleteAllTask()

    fun deleteTask(id: Int)

    fun updateTask(task: TaskDao): TaskDao
}