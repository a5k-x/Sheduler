package com.a5k.tasksheduler.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a5k.tasksheduler.R
import com.a5k.tasksheduler.databinding.FragmentTasksBinding
import com.a5k.tasksheduler.di.BaseComponent


class TasksFragment : Fragment() {

    private var binding: FragmentTasksBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BaseComponent).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}