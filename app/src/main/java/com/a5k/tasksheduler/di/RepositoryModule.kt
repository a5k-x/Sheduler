package com.a5k.tasksheduler.di

import com.a5k.tasksheduler.data.repository.TaskRepositoryImpl
import com.a5k.tasksheduler.presentation.repository.TaskRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(impl: TaskRepositoryImpl): TaskRepository
}