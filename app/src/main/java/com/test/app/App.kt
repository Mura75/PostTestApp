package com.test.app

import android.app.Application
import com.test.app.di.component.AppComponent
import com.test.app.di.component.DaggerAppComponent
import com.test.app.di.module.AppModule
import com.test.app.di.module.NetworkModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()
    }
}