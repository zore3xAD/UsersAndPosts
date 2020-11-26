package com.anton.dobrogorsky.usersposts.model

data class Post(val id: Int, val userId: Int, val title: String, val body: String, var comments: List<Comment>? = null)