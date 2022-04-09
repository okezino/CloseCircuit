package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.GetMyPlansDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyPlansUseCase @Inject constructor(private val planRepository: PlanRepository) {
    suspend operator fun invoke(limit: Int, offset: Int, authHeader: String): Flow<Resource<Result<GetMyPlansDto>>> =
        planRepository.getMyPlans(limit, offset, authHeader)
}