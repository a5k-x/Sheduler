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
import com.a5k.tasksheduler.databinding.FragmentTasksBinding
import com.a5k.tasksheduler.di.BaseComponent
import com.a5k.tasksheduler.domain.entity.Task
import com.a5k.tasksheduler.presentation.viewmodel.TaskViewModel
import com.a5k.tasksheduler.ui.view.CalendarView
import com.a5k.tasksheduler.util.observe
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
        initObserve()
        initTimeInContainer()
    }

    private fun initObserve(){
        viewModel.apply {
            observe(listTask, ::renderListTask)
        }
    }

    private fun renderListTask(list: List<Task>){
        Log.d("HARDCODE", "LIST TASK SIZE = ${list.size}")
    }

    private fun initTimeInContainer(){
        val lin = binding?.containerMm
        for (hour in 0..23) {
            lin?.addView(CalendarView(requireContext()).apply { setting(String.format("%02d:00", hour)) })
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}