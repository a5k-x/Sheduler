package com.a5k.tasksheduler.util

import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
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

fun Long.toStringTime(): String {
    return SimpleDateFormat(PATTERN_TIME).format(this)
}

fun String.toSec(pattern: String): Int {
    val time = LocalTime.parse(this, DateTimeFormatter.ofPattern(pattern))
    return ((time.hour * 3600) + (time.minute * 60))
}

fun Long.toCoordinate(heightViewPx: Int ): Int {
    val timeWithDate = SimpleDateFormat(PATTERN_TIME).format(this)
    val countSecFromTime = timeWithDate.toSec(PATTERN_TIME)
    val coordinate = parseToCoordinate(countSecFromTime, heightViewPx)
    return coordinate
}

fun parseToCoordinate(timeMSec: Int, heightView: Int)
        = (heightView * timeMSec) / 86_400

fun parseTimestampToString(times: Long, pattern: String): String =
    SimpleDateFormat(pattern).format(times)
//hardcode after delete
fun getListTime(): List<String> {
    val list = listOf("01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00",
        "14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00")
    return list
}