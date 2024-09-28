package ru.nikita.medianetwork.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.nikita.medianetwork.R
import ru.nikita.medianetwork.adapter.PostAdapter
import ru.nikita.medianetwork.databinding.ActivityMainBinding
import ru.nikita.medianetwork.utils.NumbersUtils
import ru.nikita.medianetwork.viewModel.PostViewModel


class MainActivity : AppCompatActivity() {

//    private val eyePost = 333
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()

        val adapter = PostAdapter(
            { viewModel.likeById(it.id) },
            { viewModel.shareCounter(it.id) }
        )

        binding.recyclerView.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

    }
}