package com.anton.dobrogorsky.usersposts.flow.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.anton.dobrogorsky.usersposts.model.User
import com.anton.dobrogorsky.usersposts.repository.UserRepository

class UserListViewModel(val userRepository: UserRepository) : ViewModel() {

    val userList: LiveData<List<User>> = liveData {
        with(userRepository.getUsers()) {
            if (isSuccessful) body()?.let { emit(it.sortedBy { it.username }) }
        }
    }

}