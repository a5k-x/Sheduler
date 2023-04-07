package com.a5k.tasksheduler.data.converter

import com.a5k.tasksheduler.data.model.TaskDto
import com.a5k.tasksheduler.presentation.entity.Task
import com.a5k.tasksheduler.util.getTime
import com.a5k.tasksheduler.util.toDateFinish
import com.a5k.tasksheduler.util.toDateStart

fun TaskDto.toTask() = Task(id, dateStart.getTime(), dateFinish.getTime(), name, description)

fun Task.toTaskDto() = TaskDto(id, dateStart.toDateStart(), dateFinish.toDateFinish(), name, description)