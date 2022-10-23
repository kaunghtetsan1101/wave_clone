package com.example.waveclone.ui.comment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waveclone.databinding.ItemCommentReplyBinding
import com.example.waveclone.model.pojo.CommentWithReplies

class CommentAdapter : ListAdapter<CommentWithReplies, CommentAdapter.CommentViewHolder>(
    DiffCallback()
) {


    private class DiffCallback : DiffUtil.ItemCallback<CommentWithReplies>() {
        override fun areItemsTheSame(
            oldItem: CommentWithReplies,
            newItem: CommentWithReplies
        ): Boolean {
            return oldItem.commentEntity.id == newItem.commentEntity.id
        }

        override fun areContentsTheSame(
            oldItem: CommentWithReplies,
            newItem: CommentWithReplies
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class CommentViewHolder(
        private val binding: ItemCommentReplyBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            comment: CommentWithReplies,
        ) {
            val replyAdapter = ReplyAdapter()
            with(binding) {
                this.comment = comment
                binding.rv.apply {
                    layoutManager =
                        LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
                    this.adapter = replyAdapter
                }
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            binding = ItemCommentReplyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(
            getItem(position) as CommentWithReplies
        )
    }

}