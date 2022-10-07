package com.example.waveclone.ui.sendanyone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waveclone.databinding.ItemTransactionInfoBinding
import com.example.waveclone.model.TransactionInfo

class SendAnyoneInfoAdapter :
    ListAdapter<TransactionInfo, SendAnyoneInfoAdapter.TransactionInfoViewHolder>(
        DiffCallback()
    ) {
    var tracker: SelectionTracker<String>? = null


    private class DiffCallback : DiffUtil.ItemCallback<TransactionInfo>() {
        override fun areItemsTheSame(oldItem: TransactionInfo, newItem: TransactionInfo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TransactionInfo,
            newItem: TransactionInfo
        ): Boolean {
            return false
        }
    }

    init {
        setHasStableIds(true)
    }

    inner class TransactionInfoViewHolder(
        private val binding: ItemTransactionInfoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            transactionInfo: TransactionInfo,
            isActivated: Boolean
        ) {
            with(binding) {
                this.transaction = transactionInfo
                root.isActivated = isActivated
                executePendingBindings()
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<String> =
            object : ItemDetailsLookup.ItemDetails<String>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): String =
                    getItem(adapterPosition).feeStrWithoutFormat
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionInfoViewHolder {
        return TransactionInfoViewHolder(
            binding = ItemTransactionInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TransactionInfoViewHolder, position: Int) {
        tracker?.let {
            val item = getItem(position) as TransactionInfo
            holder.bind(
                item,
                it.isSelected(item.feeStrWithoutFormat)
            )
        }
    }

    override fun getItemId(position: Int): Long = getItem(position).feeStrWithoutFormat.toLong()

}