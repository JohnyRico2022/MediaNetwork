package ru.nikita.medianetwork.viewModel

import androidx.lifecycle.ViewModel
import ru.nikita.medianetwork.repository.Repository
import ru.nikita.medianetwork.repository.RepositoryInMemoryImpl

class PostViewModel: ViewModel() {

    private val repository: Repository = RepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
    fun share() = repository.share()

}