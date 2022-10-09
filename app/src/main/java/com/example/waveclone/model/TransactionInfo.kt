package com.example.waveclone.model

import android.os.Parcelable
import com.example.waveclone.utils.toCurrencyString
import kotlinx.parcelize.Parcelize


@Parcelize
data class TransactionInfo(
    val amountFrom: Double,
    val amountTo: Double,
    val fee: Double,
    val textStr: String? = null
) : Parcelable {
    val amountFromStr: String
        get() = amountFrom.toCurrencyString()

    val amountToStr: String
        get() = amountTo.toCurrencyString()

    val feeStr: String
        get() = fee.toCurrencyString()

    val feeStrWithoutFormat: String
        get() = fee.toInt().toString()
}