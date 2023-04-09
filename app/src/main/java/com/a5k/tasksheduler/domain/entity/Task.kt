package com.a5k.tasksheduler.domain.entity

data class Task(
    val id: Int = 0,
    val dateStart: Long,
    val dateFinish: Long,
    val name: String,
    val description: String,
)

