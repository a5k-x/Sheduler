package com.a5k.tasksheduler.util

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

const val PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss"
const val PATTERN_DATE = "yyyy-MM-dd"
const val PATTERN_DATE_POINT = "dd.MM.yyyy"
const val PATTERN_TIME = "HH:mm"
const val TIME_FORMAT = "%02d:%02d"
const val DATE_FORMAT = "%02d.%02d.%d"
const val TIME_FORMAT_TEXT = "%02d:00"

fun String.toDateStart(pattern: String): Long {
    val format = SimpleDateFormat(pattern).parse(this).time
    return format
}

fun String.toDateFinish(pattern: String): Long {
    val format = SimpleDateFormat(pattern).parse(this).getDateFinish().time
    return format
}

fun Date.getDateFinish() = Calendar.getInstance().apply {
    time = this@getDateFinish
    add(Calendar.DATE, 1)
    add(Calendar.SECOND, -1)
}.time

fun Long.toStringTime(pattern: String): String {
    return SimpleDateFormat(pattern).format(this)
}

fun Long.toStringDate(pattern: String): String {
    return SimpleDateFormat(pattern).format(this)
}

fun String.toSec(pattern: String): Int {
    val time = LocalTime.parse(this, DateTimeFormatter.ofPattern(pattern))
    return ((time.hour * 3600) + (time.minute * 60))
}

fun Long.toCoordinate(heightViewPx: Int): Int {
    val timeWithDate = SimpleDateFormat(PATTERN_TIME).format(this)
    val countSecFromTime = timeWithDate.toSec(PATTERN_TIME)
    return parseToCoordinate(countSecFromTime, heightViewPx)
}

fun getTextTime(hours: Int, minutes: Int): String = String.format(TIME_FORMAT, hours, minutes)

fun parseToCoordinate(timeMSec: Int, heightView: Int): Int {
    return (heightView * timeMSec) / 86_400
}

fun String.toSumTime(time: String): Long {
    val dateTimestamp = SimpleDateFormat(PATTERN_DATE_POINT).parse(this).time
    val timeTimestamp = time.toSec(PATTERN_TIME) * 1000
    return dateTimestamp + timeTimestamp
}

//вынести
fun defaultCoroutineExceptionHandler() = CoroutineExceptionHandler { _, throwable ->
    Log.e("ERROR", throwable.message.toString())
}

fun Date.currentDay() = SimpleDateFormat(PATTERN_DATE_POINT).format(this)

fun String.getDate(pattern: String) = SimpleDateFormat(pattern).parse(this)