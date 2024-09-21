package ru.nikita.medianetwork.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.nikita.medianetwork.R
import ru.nikita.medianetwork.databinding.ActivityMainBinding
import ru.nikita.medianetwork.dto.Post
import ru.nikita.medianetwork.utils.NumbersUtils

class MainActivity : AppCompatActivity() {

    private val eyePost = 333
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            1L,
            "Некий автор",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            "22 мая 13:16",
            false,
            10,
            89
        )

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            if (post.likeByMe)
                postLikes.setImageResource(R.drawable.ic_favorite_border_24_red)
            else
                postLikes.setImageResource(R.drawable.ic_favorite_border_24_grey)

            postLikesScore.text = NumbersUtils.scoreDisplay(post.likes)
            postLikes.setOnClickListener {
                post.likeByMe = !post.likeByMe
                postLikes.setImageResource(
                    if (post.likeByMe) R.drawable.ic_favorite_border_24_red else R.drawable.ic_favorite_border_24_grey
                )
                if (post.likeByMe) post.likes++ else post.likes--
                postLikesScore.text = NumbersUtils.scoreDisplay(post.likes)
            }
            postShareScore.text = NumbersUtils.scoreDisplay(post.shares)
            postShare.setOnClickListener {
                post.shares++
                postShareScore.text = NumbersUtils.scoreDisplay(post.shares)
            }
            binding.postEyeScore.text = eyePost.toString()
        }
    }
}