package ru.nikita.medianetwork.repository

import androidx.lifecycle.LiveData
import ru.nikita.medianetwork.dto.Post

interface Repository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
}