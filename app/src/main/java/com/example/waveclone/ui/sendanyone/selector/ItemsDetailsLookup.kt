package com.example.waveclone.ui.sendanyone.selector

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.example.waveclone.ui.sendanyone.SendAnyoneInfoAdapter

class ItemsDetailsLookup(private val recyclerView: RecyclerView) : ItemDetailsLookup<String>() {
    override fun getItemDetails(e: MotionEvent): ItemDetails<String>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as SendAnyoneInfoAdapter.TransactionInfoViewHolder)
                .getItemDetails()
        }
        return null
    }
}