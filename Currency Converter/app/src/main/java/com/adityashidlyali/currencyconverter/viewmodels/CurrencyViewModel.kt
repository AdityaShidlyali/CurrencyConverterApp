package com.adityashidlyali.currencyconverter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityashidlyali.currencyconverter.helper.DispatcherProvider
import com.adityashidlyali.currencyconverter.helper.Resource
import com.adityashidlyali.currencyconverter.models.CurrencyResponse
import com.adityashidlyali.currencyconverter.repositories.DefaultCurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val repository: DefaultCurrencyRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    sealed class CurrencyEvent {
        class Success(val resultText: String) : CurrencyEvent()
        class Failure(val errorText: String) : CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }

    private val _conversion = MutableStateFlow<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: MutableStateFlow<CurrencyEvent> = _conversion

    fun convert(
        api_key: String,
        from: String,
        to: String,
        amount: String
    ) {

        val fromAmount = amount.toFloatOrNull()
        if (fromAmount == null) {
            _conversion.value = CurrencyEvent.Failure("Not a valid amount")
            return
        }

        viewModelScope.launch(dispatchers.io) {
            _conversion.value = CurrencyEvent.Loading

            when (val ratesResponse = repository.getRates(api_key, from, to, fromAmount)) {

                is Resource.Error -> _conversion.value =
                    CurrencyEvent.Failure(ratesResponse.message!!)

                is Resource.Success -> {
                    val convertedRate =
                        CurrencyResponse.convertJsonToRate(ratesResponse.data.toString(), to)

                    if (convertedRate != null) {
                        _conversion.value = CurrencyEvent.Success(
                            "$fromAmount $from = $convertedRate $to"
                        )
                    } else {
                        _conversion.value = CurrencyEvent.Failure("Something went wrong")
                    }
                }
            }
        }
    }
}