package ru.nikita.medianetwork.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.nikita.medianetwork.R
import ru.nikita.medianetwork.databinding.ActivityMainBinding
import ru.nikita.medianetwork.utils.NumbersUtils
import ru.nikita.medianetwork.viewModel.PostViewModel


class MainActivity : AppCompatActivity() {

    private val eyePost = 333
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()

        viewModel.data.observe(this) { post ->
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
                postShareScore.text = NumbersUtils.scoreDisplay(post.share)
                postShare.setOnClickListener {
                    post.share++
                    postShareScore.text = NumbersUtils.scoreDisplay(post.share)
                }
                binding.postEyeScore.text = eyePost.toString()
            }
        }

        binding.postLikes.setOnClickListener {
            viewModel.like()
        }

        binding.postShare.setOnClickListener {
            viewModel.share()
        }
    }
}