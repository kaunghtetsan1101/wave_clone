package com.example.waveclone.ui.sendanyone

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waveclone.databinding.AwonarItemExpWithImageBinding
import com.example.waveclone.model.TransactionInfo

class SendAnyoneInfoAdapter(
    private val onSelected: (TransactionInfo) -> Unit
) :
    ListAdapter<TransactionInfo, SendAnyoneInfoAdapter.TransactionInfoViewHolder>(
        DiffCallback()
    ), Filterable {
    var tracker: SelectionTracker<TransactionInfo>? = null


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
        private val binding: AwonarItemExpWithImageBinding,
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
            binding.root.setOnClickListener {
                onSelected(transactionInfo)
            }

            tracker?.let {
                if (it.isSelected(transactionInfo))
                    onSelected(transactionInfo)
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<TransactionInfo> =
            object : ItemDetailsLookup.ItemDetails<TransactionInfo>() {
                override fun getPosition(): Int = adapterPosition

                override fun getSelectionKey(): TransactionInfo? = getItem(adapterPosition)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionInfoViewHolder {
        return TransactionInfoViewHolder(
            binding = AwonarItemExpWithImageBinding.inflate(
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
                it.isSelected(item)
            )
        }
    }

    override fun getItemId(position: Int): Long = position.toLong()
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                return FilterResults()
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            }
        }
    }

}