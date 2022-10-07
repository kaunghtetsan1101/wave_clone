package com.example.waveclone.ui.sendanyone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import com.example.waveclone.databinding.FragmentSendAnyoneInfoBinding
import com.example.waveclone.ui.TransactionInfoViewModel
import com.example.waveclone.ui.sendanyone.selector.ItemsDetailsLookup
import com.example.waveclone.ui.sendanyone.selector.MyItemKeyProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendAnyoneInfoListFragment : Fragment() {
    private val sendAnyoneInfoVM by viewModels<TransactionInfoViewModel>()
    private lateinit var binding: FragmentSendAnyoneInfoBinding
    private lateinit var _sendAnyoneInfoAdapter: SendAnyoneInfoAdapter
    private var tracker: SelectionTracker<String>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendAnyoneInfoBinding.inflate(inflater, container, false).apply {
            viewModel = sendAnyoneInfoVM
            lifecycleOwner = this@SendAnyoneInfoListFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTransactionInfoAdapter()
        setUpTracker()
        observer()
    }

    private fun setUpTransactionInfoAdapter() {
        val adapter = SendAnyoneInfoAdapter()
        _sendAnyoneInfoAdapter = adapter
        binding.rv.adapter = _sendAnyoneInfoAdapter
    }

    private fun setUpTracker() {
        tracker = SelectionTracker.Builder(
            "mySelection",
            binding.rv,
            MyItemKeyProvider(_sendAnyoneInfoAdapter),
            ItemsDetailsLookup(binding.rv),
            StorageStrategy.createStringStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()
        _sendAnyoneInfoAdapter.tracker = tracker
    }

    private fun observer() {
        tracker?.addObserver(
            object : SelectionTracker.SelectionObserver<String>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    val items = tracker?.selection!!.size()
                    Log.i("KHS", "selected size : $items")
                }
            })
    }
}