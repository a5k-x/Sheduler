package com.a5k.tasksheduler.domain.entity

data class Task(
    val id: Int,
    val dateStart: Long,
    val dateFinish: Long,
    val name: String,
    val description: String,
)

