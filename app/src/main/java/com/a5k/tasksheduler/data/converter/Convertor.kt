package com.a5k.tasksheduler.data.converter

import com.a5k.tasksheduler.data.model.TaskDto
import com.a5k.tasksheduler.presentation.entity.Task

fun TaskDto.toTask() = Task(id, date_start, date_finish, name, description)

fun Task.toTaskDto() = TaskDto(id, date_start, date_finish, name, description)