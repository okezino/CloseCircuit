package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteStepUseCase @Inject constructor(private val planRepository: PlanRepository) {
    suspend operator fun invoke(stepId: String, authHeader: String): Flow<Resource<Result<String>>> =
        planRepository.deleteStep(stepId, authHeader)
}