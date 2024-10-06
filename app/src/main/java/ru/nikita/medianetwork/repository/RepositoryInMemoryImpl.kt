package ru.nikita.medianetwork.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.nikita.medianetwork.dto.Post

class RepositoryInMemoryImpl : Repository {

    private var nextId = 1L

    private var posts = listOf<Post>(
        Post(
            nextId++,
            "John Doe",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "21 мая 13:16",
            false,
            10,
            10
        ),
        Post(
            nextId++,
            "John Doe",
            "Тестовый текст поста 2",
            "22 мая 13:16",
            false,
            20,
            20
        ),
        Post(
            nextId++,
            "John Doe",
            "Тестовый текст поста 3",
            "23 мая 13:16",
            false,
            30,
            30
        ),
        Post(
            nextId++,
            "John Doe",
            "Тестовый текст поста 4",
            "24 мая 13:16",
            false,
            40,
            40
        ),
        Post(
            nextId++,
            "John Doe",
            "Тестовый текст поста 5",
            "25 мая 13:16",
            false,
            50,
            50
        ),
    )

    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) it
            else {
                if (it.likeByMe)
                    it.copy(likeByMe = !it.likeByMe, likes = it.likes - 1)
                else it.copy(likeByMe = !it.likeByMe, likes = it.likes + 1)
            }
        }
        data.value = posts
    }

    override fun shareCounter(id: Long) {
        posts = posts.map {
            if (it.id != id) it
            else it.copy(share = it.share + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        posts = if (post.id == 0L) {
            listOf(post.copy(id = nextId++, author = "Me", published = "Now")) + posts
        } else {
            posts.map { if (it.id != post.id) it else it.copy(content = post.content) }
        }
        data.value = posts
    }

}