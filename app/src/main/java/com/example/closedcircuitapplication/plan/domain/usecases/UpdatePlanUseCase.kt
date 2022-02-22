package com.example.closedcircuitapplication.plan.domain.usecases

import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.data.datadto.UpdatePlanResponseDto
import com.example.closedcircuitapplication.plan.domain.models.UpdatePlanRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdatePlanUseCase @Inject constructor(private val planRepository: PlanRepository) {
    suspend operator fun invoke(updatePlanRequest: UpdatePlanRequest, planId: String, token: String): Flow<Resource<Result<UpdatePlanResponseDto>>> =
        planRepository.updateUserPlan(updatePlanRequest, planId, token)
}