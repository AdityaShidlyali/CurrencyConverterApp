package com.adityashidlyali.currencyconverter.repositories

import com.adityashidlyali.currencyconverter.helper.Resource
import com.adityashidlyali.currencyconverter.network.CurrencyApi
import javax.inject.Inject

class DefaultCurrencyRepository @Inject constructor(
    private val currencyApi: CurrencyApi
) : MainRepository {

    private val TAG = javaClass.simpleName

    override suspend fun getRates(
        api_key: String,
        from: String,
        to: String,
        amount: Float
    ): Resource<String> {
        return try {
            val response = currencyApi.getRates(api_key, from, to, amount)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An Error occurred")
        }
    }

}