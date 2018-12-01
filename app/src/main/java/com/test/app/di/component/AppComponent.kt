package com.test.app.di.component

import dagger.Component
import com.test.app.PostRepository
import com.test.app.di.module.AppModule
import com.test.app.di.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun providePostRepository(): PostRepository
}