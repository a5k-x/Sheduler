package com.a5k.tasksheduler.util

import java.text.SimpleDateFormat
import java.util.*

const val PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss"
const val PATTERN_DATE = "yyyy-MM-dd"
const val PATTERN_TIME = "HH:mm"

fun String.toDateStart(): Long {
    val format = SimpleDateFormat(PATTERN_DATE_TIME).parse(this).time
    return format
}

fun String.toDateFinish(): Long {
    val format = SimpleDateFormat(PATTERN_DATE_TIME).parse(this).getDateFinish().time
    return format
}

fun Date.getDateFinish() = Calendar.getInstance().apply {
    time = this@getDateFinish
    add(Calendar.DATE, 1)
    add(Calendar.SECOND, -1)
}.time

fun Long.getTime(): String {
    return SimpleDateFormat(PATTERN_TIME).format(this)
}