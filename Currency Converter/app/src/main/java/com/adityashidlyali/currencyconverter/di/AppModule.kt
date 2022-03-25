package com.adityashidlyali.currencyconverter.di

import com.adityashidlyali.currencyconverter.helper.DispatcherProvider
import com.adityashidlyali.currencyconverter.helper.EndPoints
import com.adityashidlyali.currencyconverter.network.CurrencyApi
import com.adityashidlyali.currencyconverter.repositories.DefaultCurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCurrencyApi(): CurrencyApi = Retrofit.Builder()
        .baseUrl(EndPoints.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()
        .create(CurrencyApi::class.java)

    @Singleton
    @Provides
    fun providesDefaultCurrencyRepository(currencyApi: CurrencyApi) =
        DefaultCurrencyRepository(currencyApi)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }
}