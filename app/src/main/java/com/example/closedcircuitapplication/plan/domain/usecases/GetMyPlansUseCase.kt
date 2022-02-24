package com.example.closedcircuitapplication.plan.domain.usecases

import com.example.closedcircuitapplication.plan.presentation.models.GetMyPlansDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyPlansUseCase @Inject constructor(private val planRepository: PlanRepository) {
    suspend operator fun invoke(limit: Int, offset: Int, authHeader: String): Flow<Resource<Result<GetMyPlansDto>>> =
        planRepository.getMyPlans(limit, offset, authHeader)
}