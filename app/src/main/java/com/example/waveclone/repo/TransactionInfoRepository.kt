package com.example.waveclone.repo

import androidx.room.withTransaction
import com.example.waveclone.db.AppDatabase
import com.example.waveclone.db.entity.TransactionInfoEntity
import com.example.waveclone.model.TransactionInfo
import com.example.waveclone.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionInfoRepository @Inject constructor(
    private val appDatabase: AppDatabase
) {
    private val dao = appDatabase.transactionInfoDao()

    suspend fun insertTransactionInfo(transactionInfo: List<TransactionInfo>): Flow<DataState<Boolean>> =
        flow {
            emit(DataState.Loading)
            appDatabase.withTransaction {
                dao.deleteAllTransactionInfo()
                dao.insertTransactionInfo(*transactionInfo.map {
                    it.toTransactionInfoEntity()
                }.toTypedArray())
            }
            delay(2000)
            emit(DataState.Success(true))
        }

    suspend fun getAllTransactionInfo(): Flow<DataState<List<TransactionInfo>>> = flow {
        emit(DataState.Loading)
        val list = dao.getAllTransactionInfo().map {
            it.toTransactionInfo()
        }
        delay(2000)
        emit(DataState.Success(list))
    }
}

/* Mappers */
fun TransactionInfo.toTransactionInfoEntity() : TransactionInfoEntity =
    TransactionInfoEntity(
        amountFrom = amountFrom,
        amountTo = amountTo,
        fee = fee
    )

fun TransactionInfoEntity.toTransactionInfo() : TransactionInfo =
    TransactionInfo(
        amountFrom = amountFrom,
        amountTo = amountTo,
        fee = fee
    )