package com.a5k.tasksheduler.presentation.entity

data class Task(
    val id: Int,
    val dateStart: String,
    val dateFinish: String,
    val name: String,
    val description: String,
)

