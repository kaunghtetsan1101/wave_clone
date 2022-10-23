package com.example.waveclone.ui.transactionfee

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.waveclone.ui.comment.CommentFragment

class TransactionFeeFragmentStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment {
        return CommentFragment()
    }
}