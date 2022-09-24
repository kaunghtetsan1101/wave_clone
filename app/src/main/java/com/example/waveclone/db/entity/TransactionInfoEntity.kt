package com.example.waveclone.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.waveclone.utils.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TransactionInfoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    var id: Long = 0,
    @ColumnInfo(name = "AMOUNT_FROM")
    val amountFrom : Double,
    @ColumnInfo(name = "AMOUNT_TO")
    val amountTo : Double,
    @ColumnInfo(name = "FEE")
    val fee : Double
)
