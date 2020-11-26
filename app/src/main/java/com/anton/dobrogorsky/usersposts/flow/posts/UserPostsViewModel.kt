package com.anton.dobrogorsky.usersposts.flow.posts

import androidx.lifecycle.*
import com.anton.dobrogorsky.usersposts.model.Post
import com.anton.dobrogorsky.usersposts.repository.PostRepository
import kotlinx.coroutines.launch

class UserPostsViewModel(val postRepository: PostRepository) : ViewModel() {

    private val _userPosts = MutableLiveData<List<Post>>()

    val userPosts = _userPosts

    fun fetchUserPosts(userId: Int) {
        viewModelScope.launch {
            _userPosts.value = postRepository.getAllUsersPosts(userId)
        }
    }
}