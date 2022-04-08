package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.data.network.models.UpdateStepDto
import com.example.closedcircuitapplication.common.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.UpdateStepRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateStepUseCase @Inject constructor(private val planRepository: PlanRepository) {
    suspend operator fun invoke(stepId: String, authHeader: String, updateStepRequest: UpdateStepRequest): Flow<Resource<Result<UpdateStepDto>>> =
        planRepository.updateStep(stepId, authHeader, updateStepRequest)
}