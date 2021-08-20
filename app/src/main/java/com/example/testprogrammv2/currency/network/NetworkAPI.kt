package com.example.testprogrammv2.currency.network

import com.example.testprogrammv2.currency.`object`.xml.ValCurs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkAPI {

    @GET("XML_dynamic.asp")
//    @GET("latest.js")
    suspend fun getValueForMonth(
        @Query("date_req1") date1:String,
        @Query("date_req2") date2:String,
        @Query("VAL_NM_RQ") n_current:String): Response<ValCurs>
}