package com.example.waveclone.ui.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListPopupWindow
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.waveclone.databinding.FragmentTestBinding
import com.example.waveclone.model.TestModel
import com.example.waveclone.ui.test.adapter.TestBaseAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding
    private val testVM by viewModels<TestViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTestBinding.inflate(inflater, container, false).apply {
            viewModel = testVM
            lifecycleOwner = this@TestFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setIncomeSourceAdapter()
        observer()
    }

    private fun observer() {
        testVM.isSalary.observe(viewLifecycleOwner) {
            setOccupationAdapter(it)
//            binding.tiOne.isVisible = it
            binding.tv.isVisible = it
        }
    }

    private fun setOccupationAdapter(isSalary: Boolean) {
        val data = listOf(
            "One_One",
            "Two_Two",
            "Three_Three",
            "Four_Four",
            "Five_Five"
        )
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            if (isSalary) data.dropLast(1).toTypedArray() else data.toTypedArray()
        )
        arrayAdapter.notifyDataSetChanged()
        binding.atvOne.setAdapter(arrayAdapter)

        binding.atvOne.setOnItemClickListener { parent, _, position, _ ->
            println(parent.getItemAtPosition(position) as String)
        }
    }

    private fun setIncomeSourceAdapter() {

        val data = listOf(
            TestModel(
                id = 1,
                textStr = "One",
                choosed = false
            ),
            TestModel(
                id = 2,
                textStr = "Two",
                choosed = false
            ),
            TestModel(
                id = 3,
                textStr = "Three",
                choosed = false
            ),
            TestModel(
                id = 4,
                textStr = "Four",
                choosed = false
            ),
            TestModel(
                id = 5,
                textStr = "Five",
                choosed = false
            )
        )

        /*List PopUp Menu*/

        val listPopUp = ListPopupWindow(
            requireContext(),
            null,
            com.google.android.material.R.attr.listPopupWindowStyle
        )

        listPopUp.anchorView = binding.atvTwo
        val listPopUpAdapter = TestBaseAdapter(requireContext()) { list ->
            if (list.isNotEmpty()) {
                binding.atvTwo.setFormattedString(list.map {
                    it.textStr
                })
            } else {
                binding.atvTwo.setText("Please Select")
            }
            testVM.isSalary.value = list.map { it.textStr }.contains("One")
        }
        listPopUpAdapter.kemonoFriends = data
        listPopUp.setAdapter(listPopUpAdapter)

        var isCheckShowing = true
        binding.atvTwo.setOnClickListener {
            isCheckShowing = if (isCheckShowing) {
                listPopUp.show()
                !listPopUp.isShowing
            } else {
                listPopUp.dismiss()
                true
            }
        }
    }

}

fun EditText.setFormattedString(value: List<String>) {
    var str = ""
    value.forEach {
        str += StringBuilder().append(it).append(", ").toString().trim()
    }
    this.setText(str.trimEnd(','))
}