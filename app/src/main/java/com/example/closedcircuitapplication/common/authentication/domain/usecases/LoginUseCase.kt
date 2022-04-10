package com.example.closedcircuitapplication.common.authentication.domain.usecases

import com.example.closedcircuitapplication.common.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.common.authentication.data.dataDto.LoginResponseDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.domain.repository.AuthRepository
import com.example.closedcircuitapplication.common.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationRepository: AuthRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest): Flow<Resource<Result<LoginResponseDto>>> =
        authenticationRepository.login(loginRequest)
}