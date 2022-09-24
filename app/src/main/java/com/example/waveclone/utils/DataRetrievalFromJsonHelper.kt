package com.example.waveclone.utils

import android.content.Context
import com.example.waveclone.R
import com.example.waveclone.model.TransactionInfo
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import javax.inject.Inject

class DataRetrievalFromJsonHelper @Inject constructor(
    private val context: Context
) {
    private fun loadJsonData(): JSONArray? {
        val inputStream = context.resources.openRawResource(R.raw.transaction_info)
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }

    fun getTransactionInfoListFromJson(): List<TransactionInfo> {
        val data = loadJsonData()
        val transactionList = mutableListOf<TransactionInfo>()
        try {
            if (data != null) {
                for (i in 0 until data.length()) {
                    val item = data.getJSONObject(i)
                    transactionList.add(
                        TransactionInfo(
                            amountFrom = item.getDouble(AMOUNT_FROM),
                            amountTo = item.getDouble(AMOUNT_TO),
                            fee = item.getDouble(FEE)
                        )
                    )
                }
            }
            return transactionList
        } catch (e: JSONException) {
            println(e.message)
            return emptyList()
        }
    }
}