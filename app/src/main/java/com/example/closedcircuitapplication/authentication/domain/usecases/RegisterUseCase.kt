package com.example.closedcircuitapplication.authentication.domain.usecases


import com.example.closedcircuitapplication.authentication.data.datadto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.domain.repository.AuthRepository
import com.example.closedcircuitapplication.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(registerRequest: RegisterRequest): Flow<Resource<Result<RegisterResponseDto>>> =
        repository.register(registerRequest)

}

