package com.example.closedcircuitapplication.common.common.di

import com.example.closedcircuitapplication.common.common.data.network.NetworkConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
       @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(gsonConverterFactory).client(okHttpClient).build()
}