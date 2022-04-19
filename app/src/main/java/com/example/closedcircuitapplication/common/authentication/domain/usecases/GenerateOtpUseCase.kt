package com.example.closedcircuitapplication.common.authentication.domain.usecases

import com.example.closedcircuitapplication.beneficiary.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.common.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.authentication.domain.repository.AuthRepository
import com.example.closedcircuitapplication.common.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenerateOtpUseCase @Inject constructor(private val authenticationRepository: AuthRepository) {

    suspend operator fun invoke(generateOtpRequest: GenerateOtpRequest): Flow<Resource<Result<GenerateOtpDto>>> =
        authenticationRepository.generateOtp(generateOtpRequest)
}