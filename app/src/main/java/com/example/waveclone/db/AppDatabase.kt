package com.example.waveclone.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.waveclone.db.dao.TransactionInfoDao
import com.example.waveclone.db.entity.TransactionInfoEntity

@Database(
    entities = [TransactionInfoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionInfoDao() : TransactionInfoDao
}