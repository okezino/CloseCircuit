package com.example.closedcircuitapplication.beneficiary.plan.domain.usecases


import com.example.closedcircuitapplication.common.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.repository.PlanRepositoryInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateOtpUseCase @Inject constructor(private val planRepository: PlanRepositoryInterface) {
    suspend operator fun invoke(generateOtpRequest: GenerateOtpRequest): Flow<Resource<Result<GenerateOtpDto>>> =
        planRepository.generateOtp(generateOtpRequest)
}