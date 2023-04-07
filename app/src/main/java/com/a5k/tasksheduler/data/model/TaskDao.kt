package com.a5k.tasksheduler.data.model

data class TaskDao(
    val id: Int = 0,
    val date_start: Long,
    val date_finish: Long,
    val name: String,
    val description: String,
)

