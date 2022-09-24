package com.example.waveclone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.waveclone.databinding.FragmentTransactionInfoBinding
import com.example.waveclone.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionInfoListFragment : Fragment() {
    private val transactionInfoVM by viewModels<TransactionInfoViewModel>()
    private lateinit var binding : FragmentTransactionInfoBinding
    private var _transactionInfoAdapter by autoCleared<TransactionInfoAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionInfoBinding.inflate(inflater,container,false).apply {
            viewModel = transactionInfoVM
            lifecycleOwner = this@TransactionInfoListFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTransactionInfoAdapter()
        transactionInfoVM.getAllTransactionInfo()
    }

    private fun setUpTransactionInfoAdapter() {
        val adapter = TransactionInfoAdapter()
        _transactionInfoAdapter = adapter
        binding.rv.adapter = _transactionInfoAdapter
    }
}