package com.a5k.tasksheduler

import com.a5k.tasksheduler.util.getTime
import com.a5k.tasksheduler.util.toDateFinish
import com.a5k.tasksheduler.util.toDateStart
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilTest {

    @Test
    fun get_start_time(){
        val currentDate = "2011-04-24 00:00:00"
        val startTimestamp = currentDate.toDateStart()
        val finishTimestamp = currentDate.toDateFinish()

        assertEquals(1303588800000, startTimestamp)
        assertEquals(1303675199000, finishTimestamp)
    }

    @Test
    fun get_time_from_timestamp(){
        val testTimestamp = 1303675199000
        val time = testTimestamp.getTime()
        assertEquals("23:59", time)
    }
}