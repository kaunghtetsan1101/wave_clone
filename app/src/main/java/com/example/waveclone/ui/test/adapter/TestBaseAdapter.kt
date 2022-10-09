package com.example.waveclone.ui.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import com.example.waveclone.databinding.ItemDropDownBinding
import com.example.waveclone.model.TestModel

class TestBaseAdapter(
    private val context: Context,
    private val onSelectedList: (List<TestModel>) -> Unit

) : BaseAdapter(), Filterable {
    var kemonoFriends: List<TestModel> = emptyList()
    var selectedList: MutableMap<Int, TestModel> = mutableMapOf()

    override fun getCount(): Int = kemonoFriends.size

    override fun getItem(position: Int): Any? = kemonoFriends[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: ItemDropDownBinding
        if (convertView == null) {
            binding = ItemDropDownBinding.inflate(LayoutInflater.from(context), parent, false)
            binding.root.tag = binding
        } else {
            binding = convertView.tag as ItemDropDownBinding
        }
        binding.test = getItem(position) as TestModel
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            val item = (getItem(position) as TestModel).apply {
                choosed = isChecked
            }
            if (item.choosed) {
                selectedList[item.id] = item
            } else {
                selectedList.remove(item.id)
            }
            onSelectedList(selectedList.values.toMutableList())
        }

        return binding.root
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