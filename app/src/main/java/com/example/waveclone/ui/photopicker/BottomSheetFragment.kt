package com.example.waveclone.ui.photopicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.waveclone.R
import com.example.waveclone.databinding.DialogBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: DialogBinding

    companion object {
        const val TAG = "BottomSheet"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransparentBackgroundDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val modalBottomSheetBehavior = (this.dialog as BottomSheetDialog).behavior
        val list = mutableListOf<String>()
        for (i in 1..20) {
            list.add(i.toString())
        }

        binding.listView.apply {
            adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
        }
        modalBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

    }
}