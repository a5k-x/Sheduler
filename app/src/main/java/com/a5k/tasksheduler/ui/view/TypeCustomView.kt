package com.a5k.tasksheduler.ui.view

interface TypeCustomView {

    fun getType(): CustomType
}

enum class CustomType{
    CALENDAR,
    TASK
}