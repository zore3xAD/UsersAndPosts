package com.anton.dobrogorsky.usersposts.service.api

import com.anton.dobrogorsky.usersposts.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostsApi {

    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>

}