package com.adityashidlyali.currencyconverter.repositories

import com.adityashidlyali.currencyconverter.helper.Resource

interface MainRepository {

    suspend fun getRates(
        api_key: String,
        from: String,
        to: String,
        amount: Float
    ): Resource<String>

}