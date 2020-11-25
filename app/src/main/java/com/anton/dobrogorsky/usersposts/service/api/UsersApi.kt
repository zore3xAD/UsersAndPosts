package com.anton.dobrogorsky.usersposts.service.api

import com.anton.dobrogorsky.usersposts.model.User
import retrofit2.http.GET

interface UsersApi {

    @GET("/users")
    suspend fun getAllUsers() : List<User>

}