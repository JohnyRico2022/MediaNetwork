package ru.nikita.medianetwork.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.nikita.medianetwork.R
import ru.nikita.medianetwork.databinding.CardPostBinding
import ru.nikita.medianetwork.dto.Post
import ru.nikita.medianetwork.utils.NumberUtils

interface OnInteractionListener {
    fun like(post: Post)
    fun remove(post: Post)
    fun edit(post: Post)
    fun share(post: Post)
}

class PostAdapter(
    private val onInteractionListener: OnInteractionListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}


class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            postLikes.isChecked = post.likeByMe
            postLikes.text = NumberUtils.scoreDisplay(post.likes)
            postLikes.setOnClickListener {
                onInteractionListener.like(post)
            }
            postShare.text = NumberUtils.scoreDisplay(post.share)
            postShare.setOnClickListener {
                onInteractionListener.share(post)
            }
            postViewsScore.text = NumberUtils.postViews().toString()


            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.menu_options)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.remove(post)
                                true
                            }

                            R.id.edit -> {
                                onInteractionListener.edit(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }
        }
    }

}

class PostDiffCallBack : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}
