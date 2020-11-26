package com.anton.dobrogorsky.usersposts.repository

import com.anton.dobrogorsky.usersposts.model.Post
import com.anton.dobrogorsky.usersposts.service.api.PostsApi

class PostRepository(val postsApi: PostsApi) {

    suspend fun getAllUsersPosts(userId: Int): List<Post> {
        with(postsApi.getPosts()) {
            return if (isSuccessful) body()?.filter { post -> post.userId == userId } ?: emptyList()
            else emptyList()
        }
    }

}