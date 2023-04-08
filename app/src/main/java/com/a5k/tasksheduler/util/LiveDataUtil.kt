package com.a5k.tasksheduler.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> Fragment.observe(liveData: LiveData<T>, crossinline observer: (T) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer {
        observer(it)
    })