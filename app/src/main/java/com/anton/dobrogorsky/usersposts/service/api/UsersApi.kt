package com.anton.dobrogorsky.usersposts.service.api

import com.anton.dobrogorsky.usersposts.model.User
import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {

    @GET("/users")
    suspend fun getAllUsers() : Response<List<User>>

}