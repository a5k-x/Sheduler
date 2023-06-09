package com.a5k.tasksheduler.di

import android.content.Context
import com.a5k.tasksheduler.ui.fragment.TaskFragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        DispatcherModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface BaseComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): BaseComponent
    }

    @IoDispatcher
    fun dispatcher(): CoroutineDispatcher

    fun inject(taskFragment: TaskFragment)
}