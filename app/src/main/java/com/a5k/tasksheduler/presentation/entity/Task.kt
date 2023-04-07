package com.a5k.tasksheduler.presentation.entity

data class Task(
    val id: Int,
    val date_start: Long,
    val date_finish: Long,
    val name: String,
    val description: String,
)

