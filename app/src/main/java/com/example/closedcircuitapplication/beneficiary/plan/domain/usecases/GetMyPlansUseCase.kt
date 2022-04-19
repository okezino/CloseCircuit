package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.GetMyPlansDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.beneficiary.plan.domain.repository.PlanRepositoryInterface
import com.example.closedcircuitapplication.common.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyPlansUseCase @Inject constructor(private val planRepository: PlanRepositoryInterface) {
    suspend operator fun invoke(limit: Int, offset: Int): Flow<Resource<Result<GetMyPlansDto>>> =
        planRepository.getMyPlans(limit, offset)
}