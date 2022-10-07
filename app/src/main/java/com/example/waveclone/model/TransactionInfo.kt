package com.example.waveclone.model

import com.example.waveclone.utils.toCurrencyString


data class TransactionInfo(
    val amountFrom: Double,
    val amountTo: Double,
    val fee: Double
) {
    val amountFromStr: String
        get() = amountFrom.toCurrencyString()

    val amountToStr: String
        get() = amountTo.toCurrencyString()

    val feeStr: String
        get() = fee.toCurrencyString()

    val feeStrWithoutFormat: String
        get() = fee.toInt().toString()
}