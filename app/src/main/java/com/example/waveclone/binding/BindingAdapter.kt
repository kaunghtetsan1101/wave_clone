package com.example.waveclone.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waveclone.db.entity.comment.ReplyEntity
import com.example.waveclone.model.TransactionInfo
import com.example.waveclone.model.pojo.CommentWithReplies
import com.example.waveclone.ui.comment.adapter.CommentAdapter
import com.example.waveclone.ui.comment.adapter.ReplyAdapter
import com.example.waveclone.ui.sendanyone.SendAnyoneInfoAdapter

@BindingAdapter("items")
fun submitTransactionInfo(
    view: RecyclerView, items: List<TransactionInfo>?
) {
    (view.adapter as SendAnyoneInfoAdapter).submitList(
        if (items.isNullOrEmpty()) emptyList() else items
    )
}

@BindingAdapter("items")
fun submitComment(
    view: RecyclerView, items: List<CommentWithReplies>?
) {
    (view.adapter as CommentAdapter).submitList(
        if (items.isNullOrEmpty()) emptyList() else items
    )
}

@BindingAdapter("items")
fun submitReply(
    view: RecyclerView, items: List<ReplyEntity>?
) {
    (view.adapter as ReplyAdapter).submitList(
        if (items.isNullOrEmpty()) emptyList() else items
    )
}





