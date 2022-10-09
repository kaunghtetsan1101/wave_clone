package com.example.waveclone.ui.test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.waveclone.model.TestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor() : ViewModel() {

    val testData = MutableLiveData<List<TestModel>>()

    val strData = MutableLiveData<List<String>>()

    val isSalary = MutableLiveData<Boolean>()

    val isUnemployed = MutableLiveData(false)

    init {
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
        testData.postValue(data)
    }

}