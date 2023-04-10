package com.a5k.tasksheduler.data.repository

import com.a5k.tasksheduler.data.converter.toTask
import com.a5k.tasksheduler.data.converter.toTaskDto
import com.a5k.tasksheduler.data.datasource.TaskDatasource
import com.a5k.tasksheduler.domain.entity.Task
import com.a5k.tasksheduler.domain.repository.TaskRepository
import com.a5k.tasksheduler.util.PATTERN_DATE_POINT
import com.a5k.tasksheduler.util.toDateFinish
import com.a5k.tasksheduler.util.toDateStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val taskDatasource: TaskDatasource
) : TaskRepository {

    override suspend fun getAllTask(): List<Task> {
        return taskDatasource.getAllTask().mapNotNull { taskDto ->
            taskDto?.toTask()
        }
    }

    override suspend fun getTask(id: Int): List<Task> {
        return taskDatasource.getTask(id).mapNotNull { taskDto ->
            taskDto?.toTask()
        }
    }

    override suspend fun getTask(date: String): List<Task> {
        val dateStart = date.toDateStart(PATTERN_DATE_POINT)
        val dateFinish = date.toDateFinish(PATTERN_DATE_POINT)
        return taskDatasource.getTask(dateStart, dateFinish).map { taskDto ->
            taskDto.toTask()
        }
    }

    override suspend fun saveTask(task: Task) {
        taskDatasource.saveTask(task.toTaskDto())
    }

    override suspend fun saveListTask(listTask: List<Task>) {
        val listTaskDto = listTask.map { task -> task.toTaskDto() }
        taskDatasource.saveListTask(listTaskDto)
    }

    override suspend fun deleteAllTask() {
        taskDatasource.deleteAllTask()
    }

    override suspend fun deleteTask(id: Int) {
        taskDatasource.deleteTask(id)
    }

    override suspend fun updateTask(task: Task) {
        return taskDatasource.updateTask(task.toTaskDto())
    }
}