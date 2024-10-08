package ru.nikita.medianetwork.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import ru.nikita.medianetwork.R
import ru.nikita.medianetwork.adapter.OnInteractionListener
import ru.nikita.medianetwork.adapter.PostAdapter
import ru.nikita.medianetwork.databinding.ActivityMainBinding
import ru.nikita.medianetwork.dto.Post
import ru.nikita.medianetwork.utils.AndroidUtils
import ru.nikita.medianetwork.utils.AndroidUtils.focusAndShowKeyboard
import ru.nikita.medianetwork.viewModel.PostViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()

        val adapter = PostAdapter(object : OnInteractionListener {
            override fun like(post: Post) {
                viewModel.likeById(post.id)
            }
            override fun remove(post: Post) {
                viewModel.removeById(post.id)
            }
            override fun edit(post: Post) {
                binding.group.visibility = View.VISIBLE
                binding.editOriginalText.text = post.content
                viewModel.edit(post)
            }
            override fun share(post: Post) {
                viewModel.shareCounter(post.id)
            }
        })

        binding.recyclerView.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
            val newPost = posts.size > adapter.currentList.size
            adapter.submitList(posts){
                if (newPost){
                    binding.recyclerView.smoothScrollToPosition(0)
                }
            }
        }

        viewModel.edited.observe(this) {
            if (it.id != 0L) {
                binding.editText.setText(it.content)
                binding.editText.focusAndShowKeyboard()
            }
        }

        binding.save.setOnClickListener {
            val text = binding.editText.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(this, R.string.error_empty_content, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            viewModel.changeContent(text)
            viewModel.save()
            binding.editText.setText("")
            binding.editText.clearFocus()
            AndroidUtils.hideKeyboard(it)
            binding.editOriginalText.text = ""
            binding.group.visibility = View.GONE
        }

        binding.editCancel.setOnClickListener {
            binding.editText.setText(binding.editOriginalText.text)
            binding.group.visibility = View.GONE
            binding.editOriginalText.text = ""
            binding.editText.setText("")
            AndroidUtils.hideKeyboard(it)

        }

    }
}