package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.common.common.data.network.models.CreateStepDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.CreateStepsRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.repository.PlanRepositoryInterface
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow


class CreateStepUseCase @Inject constructor(
    private val planRepository: PlanRepositoryInterface
) {
    suspend operator fun invoke(createStepsRequest: CreateStepsRequest): Flow<Resource<Result<CreateStepDto>>> =
        planRepository.createStep(createStepsRequest)
}