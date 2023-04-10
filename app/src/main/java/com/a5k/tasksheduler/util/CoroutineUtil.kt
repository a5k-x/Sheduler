package com.a5k.tasksheduler.util

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler

fun defaultCoroutineExceptionHandler() = CoroutineExceptionHandler { _, throwable ->
    Log.e("ERROR", throwable.message.toString())
}

fun noAction() = Unit