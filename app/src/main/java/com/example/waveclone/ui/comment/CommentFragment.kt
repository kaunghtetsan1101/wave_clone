package com.example.waveclone.ui.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.waveclone.R
import com.example.waveclone.databinding.FragmentCommentBinding
import com.example.waveclone.ui.comment.adapter.CommentPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CommentFragment : DialogFragment() {

    private lateinit var binding: FragmentCommentBinding
    private val commentVM by viewModels<CommentViewModel>()
    private lateinit var _commentPageAdapter: CommentPagingAdapter

    override fun getTheme(): Int {
        return R.style.Theme_WaveClone
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentBinding.inflate(inflater, container, false).apply {
            viewModel = commentVM
            lifecycleOwner = this@CommentFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPagingAdapter()
        binding.btnSend.setOnClickListener {
            commentVM.isReply.value?.let {
                if (it)
                    commentVM.replyInsert()
                else
                    commentVM.commentInsert()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            commentVM.data.collectLatest {
                _commentPageAdapter.submitData(it)
            }
        }

    }

    private fun setUpPagingAdapter() {
        val adapter = CommentPagingAdapter(
            onLikeClick = {},
            onClickReplyOfReply = {
                commentVM.isReply.value = true
                commentVM.commentReplyId.value = it.commentReplyId
                val str = "@${it.userName}"
                binding.edtComment.setText(str)
            }
        ) {
            commentVM.isReply.value = true
            commentVM.commentEntity.value = it
            val str = "@${it.userName}"
            binding.edtComment.setText(str)
        }
        _commentPageAdapter = adapter
        binding.rv.adapter = _commentPageAdapter
    }
}