package com.example.closedcircuitapplication.plan.domain.usecases

import com.example.closedcircuitapplication.common.data.network.models.CreatePlanDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.presentation.models.CreatePlanRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreatePlanUseCase @Inject constructor(private val planRepository: PlanRepository) {
    suspend operator fun invoke(createPlanRequest: CreatePlanRequest, authHeader: String): Flow<Resource<Result<CreatePlanDto>>> =
        planRepository.createPlan(createPlanRequest, authHeader)
}