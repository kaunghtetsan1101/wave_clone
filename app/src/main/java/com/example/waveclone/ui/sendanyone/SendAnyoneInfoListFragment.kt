package com.example.waveclone.ui.sendanyone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.waveclone.databinding.FragmentSendAnyoneInfoBinding
import com.example.waveclone.ui.TransactionInfoViewModel
import com.example.waveclone.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendAnyoneInfoListFragment : Fragment() {
    private val sendAnyoneInfoVM by viewModels<TransactionInfoViewModel>()
    private lateinit var binding: FragmentSendAnyoneInfoBinding
    private var _sendAnyoneInfoAdapter by autoCleared<SendAnyoneInfoAdapter>()

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
    }

    private fun setUpTransactionInfoAdapter() {
        val adapter = SendAnyoneInfoAdapter()
        _sendAnyoneInfoAdapter = adapter
        binding.rv.adapter = _sendAnyoneInfoAdapter
    }
}