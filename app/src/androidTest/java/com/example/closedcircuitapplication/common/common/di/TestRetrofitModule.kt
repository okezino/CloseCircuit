package com.example.closedcircuitapplication.common.common.di

import com.example.closedcircuitapplication.common.common.data.network.AndroidTestNetworkConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [RetrofitModule::class])
class TestRetrofitModule {

    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().baseUrl(AndroidTestNetworkConstant.BASE_URL)
            .addConverterFactory(gsonConverterFactory).client(okHttpClient).build()
}