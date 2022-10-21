package com.example.waveclone.ui.sendanyone.selector

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.example.waveclone.model.TransactionInfo
import com.example.waveclone.ui.sendanyone.SendAnyoneInfoAdapter

class MyItemsDetailsLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<TransactionInfo>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<TransactionInfo>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as SendAnyoneInfoAdapter.TransactionInfoViewHolder)
                .getItemDetails()
        }
        return null
    }
}