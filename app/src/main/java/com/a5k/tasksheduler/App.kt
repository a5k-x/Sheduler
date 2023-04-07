package com.a5k.tasksheduler

import android.app.Application
import com.a5k.tasksheduler.di.BaseComponent
import com.a5k.tasksheduler.di.DaggerBaseComponent

class App: Application() {

    private lateinit var appBaseComponent: BaseComponent

    override fun onCreate() {
        super.onCreate()
        appBaseComponent = DaggerBaseComponent.factory().create(applicationContext)
    }
}