package com.a5k.tasksheduler.ui.view

interface TypeCustomView {

    fun getType(): ShedulerType
}

enum class ShedulerType{
    CALENDAR,
    TASK
}