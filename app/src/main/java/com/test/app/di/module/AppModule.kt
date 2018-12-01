package com.test.app.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import com.test.app.PostRepository
import com.test.app.network.ApiService
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun providePostRepository(apiService: ApiService): PostRepository = PostRepository(apiService)
}