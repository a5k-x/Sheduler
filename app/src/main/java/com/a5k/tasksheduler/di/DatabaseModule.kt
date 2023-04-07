package com.a5k.tasksheduler.di

import android.content.Context
import com.a5k.tasksheduler.data.database.AppDatabase
import dagger.Module
import dagger.Provides


@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(appContext: Context): AppDatabase = AppDatabase.getDatabase(appContext)
}
