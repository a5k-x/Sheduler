package com.a5k.tasksheduler.util

import android.content.Context
import android.util.Log
import com.a5k.tasksheduler.data.model.TaskDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun getListTask(context: Context): List<TaskDto> {

    lateinit var jsonString: String
    try {
        jsonString = context.assets.open("list_task.json")
            .bufferedReader()
            .use { it.readText() }
    } catch (ioException: IOException) {
        Log.e("bufferedReader", ioException.message.toString())
    }

    val type = object : TypeToken<List<TaskDto>>() {}.type
    return Gson().fromJson(jsonString, type)
}