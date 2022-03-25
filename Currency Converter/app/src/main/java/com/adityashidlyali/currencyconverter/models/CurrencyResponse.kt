package com.adityashidlyali.currencyconverter.models

import org.json.JSONObject

class CurrencyResponse {

    companion object {

        fun convertJsonToRate(jsonResponse: String, to: String): String? {
            return try {
                val mainJson: JSONObject = JSONObject(jsonResponse)

                val ratesObj: JSONObject = mainJson.getJSONObject("rates")

                val toCurrencyObjet: JSONObject = ratesObj.getJSONObject(to)

                toCurrencyObjet.getString("rate_for_amount")
            } catch (e: Exception) {
                null
            }
        }
    }
}