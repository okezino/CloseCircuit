package com.example.closedcircuitapplication.common.di

import android.content.Context
import com.example.closedcircuitapplication.common.data.network.Api
import com.example.closedcircuitapplication.common.data.preferences.ClosedCircuitPreferences
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideHeaderInterceptor(sharedPreferences: Preferences): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()

            request.addHeader("Authorization", "Bearer ${sharedPreferences.getToken()}")

            chain.proceed(request.build())
        }
    }


    @Provides
    @Singleton
    fun providesOkhttp(loggingInterceptor: HttpLoggingInterceptor, headerAuthorization: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor).build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()


    @Provides
    @Singleton
    fun provideJsonPlaceholderService(retrofit: Retrofit): Api =
        retrofit.create(Api::class.java)

    @Provides
    @Singleton
    fun providePreference(@ApplicationContext context: Context): Preferences {
        return ClosedCircuitPreferences(context)
    }

}