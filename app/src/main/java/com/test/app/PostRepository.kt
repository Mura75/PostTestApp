package com.test.app

import com.test.app.network.ApiService
import javax.inject.Inject

class PostRepository @Inject constructor(private val apiService: ApiService) {

    fun getPosts(page: Int) = apiService.getPosts(page)
        .map { result -> result.body() }
}