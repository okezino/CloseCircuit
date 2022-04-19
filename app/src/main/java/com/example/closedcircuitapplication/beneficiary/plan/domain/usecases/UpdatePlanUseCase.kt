package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.data.datadto.UpdatePlanResponseDto
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.UpdatePlanRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.repository.PlanRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdatePlanUseCase @Inject constructor(private val planRepository: PlanRepositoryInterface) {
    suspend operator fun invoke(updatePlanRequest: UpdatePlanRequest, planId: String): Flow<Resource<Result<UpdatePlanResponseDto>>> =
        planRepository.updateUserPlan(updatePlanRequest, planId)
}