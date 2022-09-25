package com.example.waveclone.ui.sendanyone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waveclone.model.TransactionInfo
import com.example.waveclone.repo.TransactionInfoRepository
import com.example.waveclone.utils.DataRetrievalFromJsonHelper
import com.example.waveclone.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SendAnyoneInfoViewModel @Inject constructor(
    private val repository: TransactionInfoRepository,
    private val dataRetrieveHelper: DataRetrievalFromJsonHelper
) : ViewModel() {
    val transactionInfoData = MutableLiveData<List<TransactionInfo>>()
    private val mShowProgress = MutableLiveData(true)
    val showProgress: LiveData<Boolean> = mShowProgress

    init {
        transactionDataLoadAndRetrieve()
    }

    private fun transactionDataLoadAndRetrieve() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val data = dataRetrieveHelper.getTransactionInfoListFromJson()
                repository.insertTransactionInfo(data)
            }

            repository.getAllTransactionInfo()
                .catch {
                    this.emit(
                        DataState.Error("Error.....")
                    )
                }
                .flowOn(Dispatchers.IO)
                .collectIndexed { _, state ->
                    when (state) {
                        is DataState.Loading -> {
                            println("Loading.........")
                            mShowProgress.postValue(true)
                        }
                        is DataState.Success -> {
                            println(state.result)
                            mShowProgress.postValue(false)
                            transactionInfoData.postValue(state.result)
                        }
                        else -> {
                            println("Error.......")
                        }
                    }
                }
        }
    }
}