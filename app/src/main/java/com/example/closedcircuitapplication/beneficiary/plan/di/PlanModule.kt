package com.example.closedcircuitapplication.beneficiary.plan.di

import com.example.closedcircuitapplication.common.authentication.data.mappers.DomainPostMapper
import com.example.closedcircuitapplication.beneficiary.plan.data.repository.PlanRepository
import com.example.closedcircuitapplication.beneficiary.plan.domain.repository.PlanRepositoryInterface
import com.example.closedcircuitapplication.common.common.data.network.webservice.AuthService
import com.example.closedcircuitapplication.common.common.data.network.webservice.BaseService
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
        baseApi: BaseService,
        authApi : AuthService,
        dispatcherProvider: DispatcherProvider
    ): PlanRepositoryInterface {
        return PlanRepository(baseApi,authApi,dispatcherProvider)
    }
}