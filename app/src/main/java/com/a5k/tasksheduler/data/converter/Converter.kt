package com.a5k.tasksheduler.data.converter

import com.a5k.tasksheduler.data.model.TaskDto
import com.a5k.tasksheduler.domain.entity.Task

fun TaskDto.toTask() = Task(id, dateStart, dateFinish, name, description)

fun Task.toTaskDto() = TaskDto(id, dateStart, dateFinish, name, description)