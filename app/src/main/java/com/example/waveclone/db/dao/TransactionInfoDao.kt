package com.example.waveclone.db.dao

import androidx.room.*
import com.example.waveclone.db.entity.TransactionInfoEntity

@Dao
interface TransactionInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactionInfo(vararg transactionInfo: TransactionInfoEntity)

    @Query("SELECT * FROM TBL_TRANSACTION_INFO ORDER BY id ASC")
    suspend fun getAllTransactionInfo(): List<TransactionInfoEntity>

    @Query("DELETE FROM TBL_TRANSACTION_INFO")
    suspend fun deleteAllTransactionInfo()
}