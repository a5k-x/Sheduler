package com.a5k.tasksheduler.data.repository

import com.a5k.tasksheduler.presentation.entity.Task
import com.a5k.tasksheduler.presentation.repository.TaskRepository
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl: TaskRepository {

    override fun getAllTask(): List<Task> {
        TODO("Not yet implemented")
    }

    override fun getTask(id: Int): List<Task> {
        TODO("Not yet implemented")
    }

    override fun getTask(date: String): List<Task> {
        TODO("Not yet implemented")
    }

    override fun saveTask(task: Task) {
        TODO("Not yet implemented")
    }

    override fun saveListTask(task: Task) {
        TODO("Not yet implemented")
    }

    override fun deleteAllTask() {
        TODO("Not yet implemented")
    }

    override fun deleteTask(id: Int) {
        TODO("Not yet implemented")
    }

    override fun updateTask(task: Task): Task {
        TODO("Not yet implemented")
    }
}