package ru.nikita.medianetwork.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.nikita.medianetwork.dto.Post

class RepositoryInMemoryImpl : Repository {

    private var post = Post(
        1L,
        "John Doe",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        "22 мая 13:16",
        false,
        66,
        96
    )

    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
        post = post.copy(
            likes = if (post.likeByMe) post.likes - 1 else post.likes + 1,
            likeByMe = !post.likeByMe
        )
        data.value = post
    }

    override fun share() {
        post = post.copy(share = post.share + 1)
        data.value = post
    }
}