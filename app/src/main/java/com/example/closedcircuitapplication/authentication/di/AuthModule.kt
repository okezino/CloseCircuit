package com.example.closedcircuitapplication.authentication.di

import com.example.closedcircuitapplication.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.common.data.network.Api
import com.example.closedcircuitapplication.common.data.repository.AuthenticationRepository
import com.example.closedcircuitapplication.common.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        api: Api,
        mapper: DomainPostMapper
    ): AuthRepository {
        return AuthenticationRepository(api, mapper)
    }
}
