package com.example.closedcircuitapplication.common.authentication.di

import com.example.closedcircuitapplication.common.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.common.common.data.network.Api
import com.example.closedcircuitapplication.common.common.data.repository.AuthenticationRepository
import com.example.closedcircuitapplication.common.common.domain.repository.AuthRepository
import com.example.closedcircuitapplication.common.common.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object AuthModule {

    @Provides
    fun provideAuthRepository(
        api: Api,
        mapper: DomainPostMapper,
        dispatcherProvider: DispatcherProvider
    ): AuthRepository {
        return AuthenticationRepository(api, mapper, dispatcherProvider)
    }
}
