package com.a5k.tasksheduler.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.a5k.tasksheduler.App
import com.a5k.tasksheduler.R
import com.a5k.tasksheduler.databinding.AddTaskLayoutBinding
import com.a5k.tasksheduler.databinding.FragmentTasksBinding
import com.a5k.tasksheduler.domain.entity.Task
import com.a5k.tasksheduler.domain.entity.Operation
import com.a5k.tasksheduler.presentation.viewmodel.TaskViewModel
import com.a5k.tasksheduler.ui.view.CellCalendarView
import com.a5k.tasksheduler.ui.view.TaskView
import com.a5k.tasksheduler.util.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject


class TaskFragment : Fragment() {

    private var binding: FragmentTasksBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: TaskViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appBaseComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModelFactory.create(TaskViewModel::class.java)
        initTimeInContainer()
        initCurrentDate()
        initObserve()
        initListener()
    }

    private fun initTimeInContainer() {
        val listCell = mutableListOf<CellCalendarView>()
        for (hour in 0..23) {
            val view = CellCalendarView(requireContext()).apply {
                setting(String.format(TIME_FORMAT_TEXT, hour))
            }
            listCell.add(view)
        }
        binding?.calendarContainer?.initCell(listCell)
    }

    private fun initObserve() {
        viewModel.apply {
            observe(listTask, ::renderListTask)
            observe(titleDay, ::renderTitleDay)
        }
    }

    private fun initCurrentDate() {
        viewModel.setTitleDate()
    }

    private fun initListener() {
        binding?.addTask?.setOnClickListener {
            openDialogAddTask()
        }
        binding?.backDate?.setOnClickListener {
            val date = binding?.currentDate?.text.toString()
            viewModel.getTask(Operation.PREVIEW, date)
        }
        binding?.nextDate?.setOnClickListener {
            val date = binding?.currentDate?.text.toString()
            viewModel.getTask(Operation.NEXT, date)
        }
    }

    private fun openDialogAddTask() {
        val view = AddTaskLayoutBinding.inflate(layoutInflater)
        MaterialAlertDialogBuilder(requireContext())
            .setView(view.root)
            .setNegativeButton(getString(R.string.cancel)) { d, _ -> d.dismiss() }
            .setPositiveButton(getString(R.string.sava)) { d, _ ->
                val date = view.dateField.text.toString()
                val name = view.nameTask.text.toString()
                val startTime = view.startTimeField.text.toString()
                val finishTime = view.endTimeField.text.toString()
                val description = view.descriptionField.text.toString()
                viewModel.saveTask(date, name, startTime, finishTime, description)
                d.dismiss()
            }.show()
        initListenerStartTime(view)
        initListenerFinishTime(view)
    }

    private fun initListenerStartTime(view: AddTaskLayoutBinding) {
        view.dateField.setOnClickListener {
            val datePicker = DatePickerFragment { _, year, month, day ->
                val date = String.format(DATE_FORMAT, day, month + 1, year)
                view.dateField.setText(date)
            }
            datePicker.show(childFragmentManager, null)
        }
    }

    private fun initListenerFinishTime(view: AddTaskLayoutBinding) {
        view.startTimeField.setOnClickListener {
            val timeStartPicker = TimePickerFragment { _, hourOfDay, minute ->
                val time = getTextTime(hourOfDay, minute)
                view.startTimeField.setText(time)
            }
            timeStartPicker.show(childFragmentManager, null)
        }
        view.endTimeField.setOnClickListener {
            val timeStartPicker = TimePickerFragment { _, hourOfDay, minute ->
                val time = getTextTime(hourOfDay, minute)
                view.endTimeField.setText(time)
            }
            timeStartPicker.show(childFragmentManager, null)
        }
    }

    private fun renderListTask(list: List<Task>) {
        val taskContainer = binding?.calendarContainer
        val listTaskView = mutableListOf<TaskView>()
        list.forEach { task ->
            val taskView = TaskView(requireContext()).apply {
                settingViewTask(task)
            }
            listTaskView.add(taskView)
        }
        taskContainer?.initTask(listTaskView)
    }

    private fun renderTitleDay(date: String) {
        binding?.currentDate?.text = date
        viewModel.listCurrentTasks(date)
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}