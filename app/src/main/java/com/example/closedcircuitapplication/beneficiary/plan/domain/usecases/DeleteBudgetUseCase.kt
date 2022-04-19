package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.beneficiary.plan.domain.repository.PlanRepositoryInterface
import com.example.closedcircuitapplication.common.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteBudgetUseCase @Inject constructor(private val planRepository: PlanRepositoryInterface) {
    suspend operator fun invoke(budgetId: String): Flow<Resource<Unit>> =
        planRepository.deleteBudget(budgetId)
}