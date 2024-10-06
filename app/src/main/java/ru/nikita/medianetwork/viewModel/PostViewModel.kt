package ru.nikita.medianetwork.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nikita.medianetwork.dto.Post
import ru.nikita.medianetwork.repository.Repository
import ru.nikita.medianetwork.repository.RepositoryInMemoryImpl

private val empty = Post(
    id = 0,
    author = "",
    content = "",
    published = "",
    likes = 0,
    share = 0,
    likeByMe = false
)
class PostViewModel: ViewModel() {

    private val repository: Repository = RepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }
    fun changeContent(content: String) {
        edited.value?.let {
            val text = content.trim()
            if (it.content != text) {
                edited.value = it.copy(content = text)
            }
        }
    }

    fun likeById(id: Long) = repository.likeById(id)
    fun shareCounter(id: Long) = repository.shareCounter(id)

    fun removeById(id: Long) = repository.removeById(id)
    fun edit(post: Post) {
        edited.value = post
    }

}