package com.example.closedcircuitapplication.plan.domain.usecases

import com.example.closedcircuitapplication.common.data.network.models.CreateStepDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.domain.models.CreateStepsRequest
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class CreateStepUseCase @Inject constructor(
    private val planRepository: PlanRepository
) {
    suspend operator fun invoke(createStepsRequest: CreateStepsRequest, authHeader: String): Flow<Resource<Result<CreateStepDto>>> =
        planRepository.createStep(createStepsRequest, authHeader)
}