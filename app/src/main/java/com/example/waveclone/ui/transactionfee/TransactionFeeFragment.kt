package com.example.waveclone.ui.transactionfee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.waveclone.databinding.FragmentTransactionFeeBinding
import com.example.waveclone.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFeeFragment : Fragment() {

    private lateinit var binding: FragmentTransactionFeeBinding
    private var _adapter by autoCleared<TransactionFeeFragmentStateAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionFeeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@TransactionFeeFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpTabLayout()
        setUpPagerAdapter()
    }

    private fun setUpTabLayout() {
        binding.tabLayout.apply {
            for (i in 0 until tabCount) {
                val tab = getTabAt(i)
                if (i == 1) tab?.select()
                tab?.view?.isClickable = false
            }
        }
    }

    private fun setUpPagerAdapter() {
        _adapter = TransactionFeeFragmentStateAdapter(this)
        binding.pager.apply {
            adapter = _adapter
        }
    }
}