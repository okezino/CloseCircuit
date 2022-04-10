package com.example.closedcircuitapplication.beneficiary.dashboard.di

import com.example.closedcircuitapplication.common.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.common.common.data.network.Api
import com.example.closedcircuitapplication.common.common.data.repository.DashboardRepository
import com.example.closedcircuitapplication.common.common.domain.repository.DashboardRepositoryInterface
import com.example.closedcircuitapplication.common.common.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object DashboardModule {

    @Provides
    fun provideDashboardRepository(
        api: Api,
        mapper: DomainPostMapper,
        dispatcherProvider: DispatcherProvider
    ): DashboardRepositoryInterface{
        return DashboardRepository(api, mapper, dispatcherProvider)
    }
}