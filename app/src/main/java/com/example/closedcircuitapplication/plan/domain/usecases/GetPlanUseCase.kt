package com.example.closedcircuitapplication.plan.domain.usecases

import com.example.closedcircuitapplication.common.data.network.models.CreatePlanDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.repository.PlanRepositoryImpl
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.presentation.models.CreatePlanRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanUseCase @Inject constructor(private val planRepositoryImpl: PlanRepositoryImpl) {
    suspend operator fun invoke(planId: String, authHeader: String): Flow<Resource<Result<CreatePlanDto>>> =
        planRepositoryImpl.getPlan(planId, authHeader)
}