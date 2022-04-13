package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.data.datadto.UpdatePlanResponseDto
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.UpdatePlanRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdatePlanUseCase @Inject constructor(private val planRepository: PlanRepository) {
    suspend operator fun invoke(updatePlanRequest: UpdatePlanRequest, planId: String, token: String): Flow<Resource<Result<UpdatePlanResponseDto>>> =
        planRepository.updateUserPlan(updatePlanRequest, planId, token)
}