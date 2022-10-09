package com.example.waveclone.ui.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.waveclone.databinding.ItemDropDownBinding
import com.example.waveclone.model.TestModel


class SimpleAdapter(context: Context, resource: Int, private val movies: List<TestModel>) :
    ArrayAdapter<TestModel>(context, resource) {

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(position: Int): TestModel {
        return movies[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val binding = ItemDropDownBinding.inflate(inflater, parent, false)
        binding.test = getItem(position)
        return binding.root
    }
}