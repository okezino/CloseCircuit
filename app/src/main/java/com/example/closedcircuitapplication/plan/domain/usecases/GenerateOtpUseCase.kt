package com.example.closedcircuitapplication.plan.domain.usecases


import com.example.closedcircuitapplication.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.repository.PlanRepositoryImpl
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.domain.models.GenerateOtpRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateOtpUseCase @Inject constructor(private val planRepositoryImpl: PlanRepositoryImpl) {
    suspend operator fun invoke(generateOtpRequest: GenerateOtpRequest): Flow<Resource<Result<GenerateOtpDto>>> =
        planRepositoryImpl.generateOtp(generateOtpRequest)
}