package com.anton.dobrogorsky.usersposts.flow.posts

import androidx.lifecycle.*
import com.anton.dobrogorsky.usersposts.model.Comment
import com.anton.dobrogorsky.usersposts.model.Post
import com.anton.dobrogorsky.usersposts.repository.PostRepository
import kotlinx.coroutines.launch

class UserPostsViewModel(val postRepository: PostRepository) : ViewModel() {

    private val _userPosts = MutableLiveData<List<Post>>()

    val userPosts = _userPosts

    val updateCommentsPostPosition = MutableLiveData<Int>()

    fun fetchUserPosts(userId: Int) {
        viewModelScope.launch {
            _userPosts.value = postRepository.getAllUsersPosts(userId).sortedBy { it.title }
        }
    }

    fun loadCommentsForPosts() {
        _userPosts.value?.forEach { post ->
            viewModelScope.launch {
                post.comments = postRepository.getPostComments(postId = post.id)
                updateCommentsPostPosition.value = _userPosts.value?.indexOf(post)
            }
        }
    }
}