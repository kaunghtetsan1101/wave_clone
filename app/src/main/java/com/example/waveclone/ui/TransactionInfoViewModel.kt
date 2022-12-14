package com.example.waveclone.ui

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
import javax.inject.Inject

@HiltViewModel
class TransactionInfoViewModel @Inject constructor(
    private val repository: TransactionInfoRepository,
    private val dataRetrieveHelper: DataRetrievalFromJsonHelper
) : ViewModel() {
    val transactionInfoData = MutableLiveData<List<TransactionInfo>>()
    val selectedItems = MutableLiveData<List<TransactionInfo>>()

    private val mShowProgress = MutableLiveData(true)
    val showProgress: LiveData<Boolean> = mShowProgress

    init {
        transactionDataLoad()
    }

    private fun transactionDataLoad() {
        viewModelScope.launch {
            val data = dataRetrieveHelper.getTransactionInfoListFromJson()
            repository.insertTransactionInfo(data)
                .catch { }
                .flowOn(Dispatchers.IO)
                .collectIndexed { _, state ->
                    when (state) {
                        is DataState.Loading -> {
                            println("Loading.........")
                            mShowProgress.postValue(true)
                        }
                        is DataState.Success -> {
                            println(state.result)
                            if (state.result)
                                transactionDataRetrieve()
                        }
                        else -> {
                            println("Error in Inserting.......")
                        }
                    }
                }


        }
    }

    private fun transactionDataRetrieve() {
        viewModelScope.launch {
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

                            val list = listOf(
                                TransactionInfo(
                                    amountFrom = 0.0,
                                    amountTo = 0.0,
                                    fee = 100.0,
                                    textStr = "Short term investment (Trade more than 10 times a week)"
                                ),
                                TransactionInfo(
                                    amountFrom = 0.0,
                                    amountTo = 0.0,
                                    fee = 200.0,
                                    textStr = "Long term investment (less than 10 trading per month)"
                                ),
                                TransactionInfo(
                                    amountFrom = 0.0,
                                    amountTo = 0.0,
                                    fee = 300.0,
                                    textStr = "Use robots to trade (I have knowledge of using robots in trading)"
                                ),
                                TransactionInfo(
                                    amountFrom = 0.0,
                                    amountTo = 0.0,
                                    fee = 400.0,
                                    textStr = "Copy-trade (I prefer copy trading than manual trading.)"
                                )
                            )

                            transactionInfoData.postValue(list)
                        }
                        else -> {
                            println("Error.......")
                        }
                    }
                }
        }
    }
}