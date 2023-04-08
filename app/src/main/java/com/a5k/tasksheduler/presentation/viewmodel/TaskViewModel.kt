package com.a5k.tasksheduler.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a5k.tasksheduler.domain.entity.Task
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskViewModel @Inject constructor(): ViewModel() {

    private val _listTask = MutableLiveData<List<Task>>()
    val listTask: LiveData<List<Task>> = _listTask

    fun listTask(){
        _listTask.value = listTestTaks
    }

    val task0 = Task(id = 1, 1668768932000, 1668969532000, "Name Task", "Description task more info task")
    val task1 = Task(id = 2, 1668981658000, 1668984490000,"Name Task","Description task more info task Description task more info task Description task more info task")

    val listTestTaks = listOf<Task>(task0, task1)


}