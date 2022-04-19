package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.common.common.data.network.models.CreatePlanDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.CreatePlanRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.repository.PlanRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreatePlanUseCase @Inject constructor(private val planRepository: PlanRepositoryInterface) {
    suspend operator fun invoke(createPlanRequest: CreatePlanRequest): Flow<Resource<Result<CreatePlanDto>>> =
        planRepository.createPlan(createPlanRequest)
}