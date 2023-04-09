package com.a5k.tasksheduler.data.database

import androidx.room.*
import com.a5k.tasksheduler.data.model.TaskDto
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    suspend fun getAllTask(): List<TaskDto?>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTask(id: Int): List<TaskDto?>

    @Query("SELECT * FROM task WHERE date_start >= :dateStart AND date_finish <= :dateFinish")
    suspend fun getTask(dateStart: Long, dateFinish: Long): List<TaskDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTask(task: TaskDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveListTask(task: List<TaskDto>)

    @Query("DELETE FROM task WHERE id > 0")
    suspend fun deleteAllTask()

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteTask(id: Int)

    @Update
    suspend fun updateTask(task: TaskDto)
}