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
import com.a5k.tasksheduler.databinding.AddTaskLayoutBinding
import com.a5k.tasksheduler.databinding.FragmentTasksBinding
import com.a5k.tasksheduler.domain.entity.Task
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
        initObserve()
        initListener()
        viewModel.listTask()

    }

    private fun initObserve() {
        viewModel.apply {
            observe(listTask, ::renderListTask)
        }
    }

    private fun initListener() {
        binding?.addTask?.setOnClickListener {
            openDialogAddTask()
        }
    }

    private fun openDialogAddTask() {
        val view = AddTaskLayoutBinding.inflate(layoutInflater)
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(view.root)
            .setNegativeButton("Cancel") { d, _ -> d.dismiss() }
            .setPositiveButton("Save") { d, _ ->
                val date = view.dateField.text.toString()
                val name = view.nameTask.text.toString()
                val startTime = view.startTimeField.text.toString()
                val finishTime = view.endTimeField.text.toString()
                val description = view.descriptionField.text.toString()
                viewModel.saveTask(date, name, startTime, finishTime, description)
                d.dismiss()
            }
        dialog.show()
        initListenerStartTime(view)
        initListenerFinishTime(view)
    }

    private fun initListenerStartTime(view: AddTaskLayoutBinding) {
        view.dateField.setOnClickListener {
            val datePicker = DatePickerFragment { _, year, month, day, ->
                val date = String.format(DATE_FORMAT, day, month, year)
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
        val lin = binding?.containerMm
        Log.d("HARDCODE", "LIST TASK SIZE = ${list.size}")
        list.forEach { task ->
            lin?.addView(TaskView(requireContext()).apply {
                settingViewTask(task)
            })
        }
    }

    private fun initTimeInContainer() {
        val lin = binding?.containerMm
        for (hour in 0..23) {
            lin?.addView(CellCalendarView(requireContext()).apply { setting(String.format("%02d:00", hour)) })
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}