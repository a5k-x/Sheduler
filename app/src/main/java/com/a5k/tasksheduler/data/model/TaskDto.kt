package com.a5k.tasksheduler.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "task")
data class TaskDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "date_start")
    @SerializedName("date_start")
    val dateStart: Long,
    @SerializedName("date_finish")
    @ColumnInfo(name = "date_finish")
    val dateFinish: Long,
    val name: String,
    val description: String,
)