package com.anton.dobrogorsky.usersposts.service.api

import com.anton.dobrogorsky.usersposts.model.Comment
import com.anton.dobrogorsky.usersposts.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {

    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("/comments?")
    suspend fun getPostComments(@Query("postId") postId: Int): Response<List<Comment>>
}