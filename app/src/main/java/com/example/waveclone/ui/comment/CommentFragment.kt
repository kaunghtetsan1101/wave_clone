package com.example.waveclone.ui.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waveclone.databinding.FragmentCommentBinding
import com.example.waveclone.ui.comment.adapter.CommentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentFragment : Fragment() {

    private lateinit var binding: FragmentCommentBinding
    private lateinit var _commentAdapter: CommentAdapter
    private val commentVM by viewModels<CommentViewModel>()

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
        setUpCommentAdapter()
        binding.btnSend.setOnClickListener {
            commentVM.commentInsert()
        }
    }

    private fun setUpCommentAdapter() {
        val adapter = CommentAdapter()
        _commentAdapter = adapter
        binding.rv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = _commentAdapter
        }
    }
}