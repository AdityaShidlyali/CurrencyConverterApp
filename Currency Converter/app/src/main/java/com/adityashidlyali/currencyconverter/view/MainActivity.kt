package com.adityashidlyali.currencyconverter.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.adityashidlyali.currencyconverter.R
import com.adityashidlyali.currencyconverter.databinding.ActivityMainBinding
import com.adityashidlyali.currencyconverter.helper.EndPoints
import com.adityashidlyali.currencyconverter.viewmodels.CurrencyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = javaClass.simpleName

    private lateinit var binding: ActivityMainBinding

    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currencyRates: Array<String> = resources.getStringArray(R.array.currency_codes)
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, currencyRates)

        binding.autoCompleteTextviewFromCurrency.setAdapter(adapter)
        binding.autoCompleteTextviewToCurrency.setAdapter(adapter)

        binding.autoCompleteTextviewFromCurrency.addTextChangedListener {
            binding.textViewPreviewFromCurrency.text = it!!.split(" ")[0]
        }

        binding.buttonConvert.setOnClickListener {

            viewModel.convert(
                EndPoints.API_KEY,
                binding.autoCompleteTextviewFromCurrency.text.toString().split(" ")[0],
                binding.autoCompleteTextviewToCurrency.text.toString().split(" ")[0],
                binding.editTextAmount.text.toString()
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when (event) {
                    is CurrencyViewModel.CurrencyEvent.Success -> {
                        binding.progressBar.isVisible = false
                        binding.textViewConvertedResult.text = event.resultText
                    }

                    is CurrencyViewModel.CurrencyEvent.Failure -> {
                        binding.progressBar.isVisible = false
                        binding.textViewConvertedResult.text = event.errorText
                    }

                    is CurrencyViewModel.CurrencyEvent.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    else -> {
                        Unit
                    }
                }
            }
        }
    }
}