package com.example.waveclone.ui.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waveclone.databinding.ItemDropDownBinding
import com.example.waveclone.model.TestModel

class TestAdapter : ListAdapter<TestModel, TestAdapter.TestViewHolder>(
    DiffCallback()
), Filterable {

    private class DiffCallback : DiffUtil.ItemCallback<TestModel>() {
        override fun areItemsTheSame(oldItem: TestModel, newItem: TestModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TestModel,
            newItem: TestModel
        ): Boolean {
            return false
        }
    }

    init {
        setHasStableIds(true)
    }

    inner class TestViewHolder(
        private val binding: ItemDropDownBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            testModel: TestModel
        ) {
            with(binding) {
                this.test = testModel
                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        return TestViewHolder(
            binding = ItemDropDownBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: TestViewHolder,
        position: Int
    ) {
        val item = getItem(position) as TestModel
        holder.bind(
            item
        )

    }

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