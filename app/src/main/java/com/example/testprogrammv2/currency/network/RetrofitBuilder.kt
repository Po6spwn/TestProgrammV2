package com.example.testprogrammv2.currency.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://cbr.ru/scripts/"
//    private const val BASE_URL = "https://www.cbr-xml-daily.ru/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
    }

    val apiService: NetworkAPI = getRetrofit().create(NetworkAPI::class.java)
}