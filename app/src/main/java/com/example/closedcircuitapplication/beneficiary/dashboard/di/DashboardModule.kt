package com.example.closedcircuitapplication.beneficiary.dashboard.di

import com.example.closedcircuitapplication.common.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.beneficiary.dashboard.data.datasource.DashboardRepository
import com.example.closedcircuitapplication.beneficiary.dashboard.domain.repository.DashboardRepositoryInterface
import com.example.closedcircuitapplication.common.common.data.network.webservice.BaseService
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
        api: BaseService,
        dispatcherProvider: DispatcherProvider
    ): DashboardRepositoryInterface {
        return DashboardRepository(api,dispatcherProvider)
    }
}