package com.example.testprogrammv2.currency.network

import com.example.testprogrammv2.currency.`object`.xml.RecordV

data class ConnectionResult (
    val success: ArrayList<RecordV>? = null,
    val error: String? = null
)