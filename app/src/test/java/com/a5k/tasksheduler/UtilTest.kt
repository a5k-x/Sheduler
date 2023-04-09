package com.a5k.tasksheduler

import com.a5k.tasksheduler.util.*
import org.junit.Test

import org.junit.Assert.*
import java.util.Date

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilTest {

    @Test
    fun start_time() {
        val currentDate = "2011-04-24 00:00:00"
        val startTimestamp = currentDate.toDateStart()
        val finishTimestamp = currentDate.toDateFinish()

        assertEquals(1303588800000, startTimestamp)
        assertEquals(1303675199000, finishTimestamp)
    }

    @Test
    fun time_from_timestamp() {
        val testTimestamp = 1303675199000
        val time = testTimestamp.toStringTime()
        assertEquals("23:59", time)
    }

    @Test
    fun coordinate_from_time() {
        val testTimestamp = 1303675199000
        val coordinate = testTimestamp.toCoordinate(1200)
        assertEquals(1199, coordinate)
    }

    @Test
    fun sum_date_and_current_time() {
        val currentdate = "24.04.2011"
        val time = "00:01"
        val actual = currentdate.toSumTime(time)
        assertEquals(1303588860000, actual)
    }
}