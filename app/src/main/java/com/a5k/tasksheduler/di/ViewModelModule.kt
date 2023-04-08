package com.a5k.tasksheduler.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a5k.tasksheduler.di.mvvm.ViewModelFactory
import com.a5k.tasksheduler.di.mvvm.ViewModelKey
import com.a5k.tasksheduler.presentation.viewmodel.TaskViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TaskViewModel::class)
    internal abstract fun provideTaskViewModel(viewModel: TaskViewModel): ViewModel

    @Binds
    abstract fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

