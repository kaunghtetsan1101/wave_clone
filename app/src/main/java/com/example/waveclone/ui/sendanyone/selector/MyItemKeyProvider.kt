package com.example.waveclone.ui.sendanyone.selector

import androidx.recyclerview.selection.ItemKeyProvider
import com.example.waveclone.ui.sendanyone.SendAnyoneInfoAdapter

class MyItemKeyProvider(private val adapter: SendAnyoneInfoAdapter) :
    ItemKeyProvider<String>(SCOPE_CACHED) {
    override fun getKey(position: Int): String {
        return adapter.currentList[position].feeStrWithoutFormat
    }

    override fun getPosition(key: String): Int {
        return adapter.currentList.indexOfFirst { it.feeStrWithoutFormat == key }
    }


}