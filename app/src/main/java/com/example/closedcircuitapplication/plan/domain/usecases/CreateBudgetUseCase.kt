package com.example.closedcircuitapplication.plan.domain.usecases

import com.example.closedcircuitapplication.common.data.network.models.CreateBudgetDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.repository.PlanRepository
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.domain.models.CreateBudgetRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateBudgetUseCase @Inject constructor(private val repository: PlanRepository) {
    suspend operator fun invoke(createBudgetRequest: CreateBudgetRequest, authHeader: String): Flow<Resource<Result<CreateBudgetDto>>> =
        repository.createBudget(createBudgetRequest, authHeader)
}