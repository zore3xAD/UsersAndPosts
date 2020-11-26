package com.anton.dobrogorsky.usersposts.repository

import com.anton.dobrogorsky.usersposts.service.api.UsersApi

class UserRepository (val usersApi: UsersApi) {

    suspend fun getUsers() = usersApi.getAllUsers()

}