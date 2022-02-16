package com.example.closedcircuitapplication.authentication.domain.usecases

import com.example.closedcircuitapplication.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.authentication.data.datadto.LoginResponseDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.domain.repository.AuthRepository
import com.example.closedcircuitapplication.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationRepository: AuthRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest): Flow<Resource<Result<LoginResponseDto>>> =
        authenticationRepository.login(loginRequest)
}