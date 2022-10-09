package com.example.waveclone.ui.sendanyone.selector

import androidx.recyclerview.selection.ItemKeyProvider
import com.example.waveclone.model.TransactionInfo
import com.example.waveclone.ui.sendanyone.SendAnyoneInfoAdapter

class MyItemKeyProvider(private val adapter: SendAnyoneInfoAdapter) :
    ItemKeyProvider<TransactionInfo>(SCOPE_CACHED) {

    override fun getPosition(key: TransactionInfo): Int {
        return adapter.currentList.indexOfFirst { it == key }
    }

    override fun getKey(position: Int): TransactionInfo? {
        return adapter.currentList[position]
    }


}