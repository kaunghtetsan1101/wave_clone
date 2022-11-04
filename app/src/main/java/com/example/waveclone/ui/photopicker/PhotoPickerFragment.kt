package com.example.waveclone.ui.photopicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.waveclone.databinding.FragmentPhotoPickerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoPickerFragment : Fragment() {

    private lateinit var binding: FragmentPhotoPickerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotoPickerBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSFragment = BottomSheetFragment()



        binding.btnShow.setOnClickListener {
            bottomSFragment.show(childFragmentManager, BottomSheetFragment.TAG)
        }
    }
}