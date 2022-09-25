package com.example.waveclone.ui.sendanyone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waveclone.databinding.ItemTransactionInfoBinding
import com.example.waveclone.model.TransactionInfo

class SendAnyoneInfoAdapter :
    ListAdapter<TransactionInfo, SendAnyoneInfoAdapter.TransactionInfoViewHolder>(
        DiffCallback()
    ) {

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

    class TransactionInfoViewHolder(
        private val binding: ItemTransactionInfoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            transactionInfo: TransactionInfo,
        ) {
            with(binding) {
                this.transaction = transactionInfo
                executePendingBindings()
            }
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
        holder.bind(
            (getItem(position) as TransactionInfo)
        )
    }

}