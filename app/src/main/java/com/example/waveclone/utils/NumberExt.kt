package com.example.waveclone.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

private const val currPattern = "##,###,###"

fun Double.toCurrencyString(): String =
    (NumberFormat.getNumberInstance(Locale.US) as DecimalFormat).apply {
        applyPattern(currPattern)
    }.format(this)


