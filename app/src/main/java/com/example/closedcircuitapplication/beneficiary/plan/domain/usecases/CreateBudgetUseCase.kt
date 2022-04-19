package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.common.common.data.network.models.CreateBudgetDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.CreateBudgetRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.repository.PlanRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateBudgetUseCase @Inject constructor(private val repository: PlanRepositoryInterface) {
    suspend operator fun invoke(createBudgetRequest: CreateBudgetRequest): Flow<Resource<Result<CreateBudgetDto>>> =
        repository.createBudget(createBudgetRequest)
}