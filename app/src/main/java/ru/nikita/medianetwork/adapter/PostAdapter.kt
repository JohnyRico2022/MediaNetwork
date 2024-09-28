package ru.nikita.medianetwork.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.nikita.medianetwork.R
import ru.nikita.medianetwork.databinding.CardPostBinding
import ru.nikita.medianetwork.dto.Post
import ru.nikita.medianetwork.utils.NumbersUtils

typealias OnLikeListener = (post: Post) -> Unit

class PostAdapter(
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnLikeListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return PostViewHolder(binding, onLikeListener, onShareListener)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}
class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onShareListener: OnLikeListener
) : RecyclerView.ViewHolder(binding.root){
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            postLikes.setImageResource(
                if (post.likeByMe) R.drawable.ic_favorite_border_24_red else R.drawable.ic_favorite_border_24_grey
            )
            postLikesScore.text = NumbersUtils.scoreDisplay(post.likes)
            postLikes.setOnClickListener {
                onLikeListener(post)
            }
            postShareScore.text = NumbersUtils.scoreDisplay(post.share)
            postShare.setOnClickListener {
                onShareListener(post)
            }
            postViewsScore.text = NumbersUtils.postViews().toString()
        }
    }
}
class PostDiffCallBack: DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}