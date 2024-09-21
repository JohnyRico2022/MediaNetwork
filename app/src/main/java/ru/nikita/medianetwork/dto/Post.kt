package ru.nikita.medianetwork.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likeByMe: Boolean = false,
    var likes: Int = 0,
    var shares: Int = 0
)
