package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.common.common.data.network.models.CreateStepDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.CreateStepsRequest
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class CreateStepUseCase @Inject constructor(
    private val planRepository: PlanRepository
) {
    suspend operator fun invoke(createStepsRequest: CreateStepsRequest, authHeader: String): Flow<Resource<Result<CreateStepDto>>> =
        planRepository.createStep(createStepsRequest, authHeader)
}