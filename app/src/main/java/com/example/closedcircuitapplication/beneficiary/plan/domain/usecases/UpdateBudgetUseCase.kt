package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases

import com.example.closedcircuitapplication.beneficiary.plan.domain.models.UpdateBudgetRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.repository.PlanRepositoryInterface
import javax.inject.Inject

class UpdateBudgetUseCase @Inject constructor(private val planRepository: PlanRepositoryInterface) {
    suspend operator fun invoke(budgetId: String,updateBudgetRequest: UpdateBudgetRequest) =
        planRepository.updateBudget(budgetId,updateBudgetRequest)
}