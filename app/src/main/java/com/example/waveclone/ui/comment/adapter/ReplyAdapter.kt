package com.example.waveclone.ui.comment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waveclone.databinding.ItemReplyBinding
import com.example.waveclone.db.entity.comment.ReplyEntity

class ReplyAdapter(
    private val onClickReplyOfReply: (ReplyEntity) -> Unit
) : ListAdapter<ReplyEntity, ReplyAdapter.ReplyViewHolder>(
    DiffCallback()
) {


    private class DiffCallback : DiffUtil.ItemCallback<ReplyEntity>() {
        override fun areItemsTheSame(oldItem: ReplyEntity, newItem: ReplyEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ReplyEntity,
            newItem: ReplyEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class ReplyViewHolder(
        private val binding: ItemReplyBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            reply: ReplyEntity,
        ) {
            with(binding) {
                this.reply = reply
                binding.btnReply.setOnClickListener {
                    onClickReplyOfReply(reply)
                }
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyViewHolder {
        return ReplyViewHolder(
            binding = ItemReplyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReplyViewHolder, position: Int) {

        holder.bind(
            getItem(position) as ReplyEntity
        )

    }

}