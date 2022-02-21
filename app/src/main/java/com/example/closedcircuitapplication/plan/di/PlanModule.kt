package com.example.closedcircuitapplication.plan.di

import com.example.closedcircuitapplication.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.common.data.network.Api
import com.example.closedcircuitapplication.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.domain.repository.PlanRepositoryInterface
import com.example.closedcircuitapplication.common.utils.DispatcherProvider
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