package ru.nikita.medianetwork.viewModel

import androidx.lifecycle.ViewModel
import ru.nikita.medianetwork.repository.Repository
import ru.nikita.medianetwork.repository.RepositoryInMemoryImpl

class PostViewModel: ViewModel() {

    private val repository: Repository = RepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
    fun shareCounter(id: Long) = repository.shareCounter(id)

}