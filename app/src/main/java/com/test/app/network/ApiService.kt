package com.test.app.network

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import com.test.app.model.Post
import com.test.app.model.PostResponse
import retrofit2.http.Query

interface ApiService {

    @GET("search_by_date?tags=story")
    fun getPosts(@Query("page") page: Int): Single<Response<PostResponse>>
}