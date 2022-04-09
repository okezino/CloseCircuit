package com.example.closedcircuitapplication.beneficiary.plan.di

import com.example.closedcircuitapplication.common.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.common.common.data.network.Api
import com.example.closedcircuitapplication.common.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.common.domain.repository.PlanRepositoryInterface
import com.example.closedcircuitapplication.common.common.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object PlanModule {

    @Provides
    fun providePlanRepository(
        api: Api,
        mapper: DomainPostMapper,
        dispatcherProvider: DispatcherProvider
    ): PlanRepositoryInterface{
        return PlanRepository(api, mapper, dispatcherProvider)
    }
}