package com.adityashidlyali.currencyconverter.network

import com.adityashidlyali.currencyconverter.helper.EndPoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET(EndPoints.CONVERT_ENDPOINT)
    suspend fun getRates(
        @Query("api_key") api_key: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Float
    ): Response<String>

}