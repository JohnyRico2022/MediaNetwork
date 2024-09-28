package ru.nikita.medianetwork.repository

import androidx.lifecycle.LiveData
import ru.nikita.medianetwork.dto.Post

interface Repository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun shareCounter(id: Long)
}
