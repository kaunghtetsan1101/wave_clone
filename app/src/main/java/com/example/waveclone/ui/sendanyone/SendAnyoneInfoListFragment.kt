package com.example.waveclone.ui.sendanyone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.GridLayoutManager
import com.example.waveclone.R
import com.example.waveclone.databinding.FragmentSendAnyoneInfoBinding
import com.example.waveclone.model.TransactionInfo
import com.example.waveclone.ui.TransactionInfoViewModel
import com.example.waveclone.ui.comment.CommentFragment
import com.example.waveclone.ui.sendanyone.selector.MyItemKeyProvider
import com.example.waveclone.ui.sendanyone.selector.MyItemsDetailsLookup
import com.example.waveclone.ui.sendanyone.selector.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendAnyoneInfoListFragment : Fragment() {
    private val sendAnyoneInfoVM by viewModels<TransactionInfoViewModel>()
    private lateinit var binding: FragmentSendAnyoneInfoBinding
    private lateinit var _sendAnyoneInfoAdapter: SendAnyoneInfoAdapter
    private var tracker: SelectionTracker<TransactionInfo>? = null

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

        binding.btnSend.setOnClickListener {
            val dialogFragment = CommentFragment()
            dialogFragment.show(childFragmentManager, "comment")
        }
    }

    private fun setUpTransactionInfoAdapter() {
        var fourthItem: TransactionInfo? = null
        val adapter = SendAnyoneInfoAdapter { info ->
            println(info)
            if (info.textStr == "Copy-trade (I prefer copy trading than manual trading.)") {
                fourthItem = info
                tracker?.apply {
                    if (hasSelection()) {
                        clearSelection()
                    }
                    select(info)
                }
            } else {
                tracker?.apply {
                    if (isSelected(fourthItem)) {
                        clearSelection()
                    }
                    select(info)
                }
            }
        }
        _sendAnyoneInfoAdapter = adapter
        binding.rv.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen._10dp)))
            this.adapter = _sendAnyoneInfoAdapter
        }
    }

    private fun setUpTracker() {
        tracker = SelectionTracker.Builder(
            "mySelection",
            binding.rv,
            MyItemKeyProvider(_sendAnyoneInfoAdapter),
            MyItemsDetailsLookup(binding.rv),
            StorageStrategy.createParcelableStorage(TransactionInfo::class.java)
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()
        _sendAnyoneInfoAdapter.tracker = tracker
    }

    private fun observer() {
        with(sendAnyoneInfoVM) {
            tracker?.addObserver(
                object : SelectionTracker.SelectionObserver<TransactionInfo>() {
                    override fun onSelectionChanged() {
                        super.onSelectionChanged()
                        selectedItems.value = tracker?.selection?.toMutableList()
                    }
                })

            selectedItems.observe(viewLifecycleOwner) {

            }
        }
    }
}