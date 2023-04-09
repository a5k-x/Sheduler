package com.a5k.tasksheduler.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a5k.tasksheduler.domain.entity.Operation
import com.a5k.tasksheduler.domain.entity.Task
import com.a5k.tasksheduler.domain.repository.TaskRepository
import com.a5k.tasksheduler.util.*
import kotlinx.coroutines.launch
import java.time.Duration
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    private val _listTask = MutableLiveData<List<Task>>()
    var listTask: LiveData<List<Task>> = _listTask

    private val _titleDay = MutableLiveData<String>()
    val titleDay: LiveData<String> = _titleDay

    fun saveTask(selectDate: String, name: String, timeStart: String, timeFinish: String, description: String) {
        viewModelScope.launch(defaultCoroutineExceptionHandler()) {
            val newTask = Task(
                name = name,
                dateStart = selectDate.toSumTime(timeStart),
                dateFinish = selectDate.toSumTime(timeFinish),
                description = description
            )
            taskRepository.saveTask(newTask)
            _titleDay.value = selectDate
        }
    }

    fun listCurrentTasks(date: String) {
        viewModelScope.launch(defaultCoroutineExceptionHandler()) {
            val listTask = taskRepository.getTask(date)
            _listTask.value = listTask
        }
    }

    fun setTitleDate() {
        viewModelScope.launch(defaultCoroutineExceptionHandler()) {
            val currentDay = Date().currentDay()
            _titleDay.value = currentDay
        }
    }

    fun getTask(typeOperation: Operation, date: String) {
        viewModelScope.launch(defaultCoroutineExceptionHandler()) {
            when (typeOperation) {
                Operation.PREVIEW -> {
                    val previewDay = date.getDate(PATTERN_DATE_POINT).time - Duration.ofDays(1).toMillis()
                    _titleDay.value = previewDay.toStringDate(PATTERN_DATE_POINT)
                }
                Operation.NEXT -> {
                    val nextDay = date.getDate(PATTERN_DATE_POINT).time + Duration.ofDays(1).toMillis()
                    _titleDay.value = nextDay.toStringDate(PATTERN_DATE_POINT)
                }
            }
        }
    }
}