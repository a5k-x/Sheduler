package com.a5k.tasksheduler.di

import com.a5k.tasksheduler.data.datasource.TaskDatasource
import com.a5k.tasksheduler.data.datasource.TaskDatasourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {

    @Binds
    abstract fun getDataSource(impl: TaskDatasourceImpl): TaskDatasource
}