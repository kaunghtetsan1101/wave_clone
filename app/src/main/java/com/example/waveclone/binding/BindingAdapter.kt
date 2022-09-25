package com.example.waveclone.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waveclone.model.TransactionInfo
import com.example.waveclone.ui.sendanyone.SendAnyoneInfoAdapter

@BindingAdapter("items")
fun submitTransactionInfo(
    view: RecyclerView, items: List<TransactionInfo>?
) {
    (view.adapter as SendAnyoneInfoAdapter).submitList(
        if (items.isNullOrEmpty()) emptyList() else items
    )
}




