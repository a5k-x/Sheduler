package com.a5k.tasksheduler.data.converter

import com.a5k.tasksheduler.data.model.TaskDao
import com.a5k.tasksheduler.presentation.entity.Task

fun TaskDao.toTask() = Task(id, date_start, date_finish, name, description)

fun Task.toTaskDao() = TaskDao(id, date_start, date_finish, name, description)