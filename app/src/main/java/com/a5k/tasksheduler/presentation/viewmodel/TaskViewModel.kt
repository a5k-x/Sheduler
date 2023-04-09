package com.a5k.tasksheduler.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a5k.tasksheduler.domain.entity.Task
import com.a5k.tasksheduler.domain.repository.TaskRepository
import com.a5k.tasksheduler.util.defaultCoroutineExceptionHandler
import com.a5k.tasksheduler.util.toSumTime
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {

    private val _listTask = MutableLiveData<List<Task>>()
    val listTask: LiveData<List<Task>> = _listTask


    fun listTask(){
        _listTask.value = listTestTaks
    }

    fun saveTask(selectDate: String, name: String, timeStart: String, timeFinish: String, description: String) {
        viewModelScope.launch(defaultCoroutineExceptionHandler()) {
            val newTask = Task(
                name = name,
                dateStart = selectDate.toSumTime(timeStart),
                dateFinish = selectDate.toSumTime(timeFinish),
                description = description)
            taskRepository.saveTask(newTask)
        }
    }



    val task0 = Task(id = 1, 1668768932000, 1668969532000, "Name Task", "Description task more info task")
    val task1 = Task(id = 2, 1668981658000, 1668984490000,"Name Task","Description task more info task Description task more info task Description task more info task")

    val listTestTaks = listOf<Task>(task0, task1)


}