package com.example.closedcircuitapplication.common.common.domain.repository

import com.example.closedcircuitapplication.common.authentication.data.dataDto.LoginResponseDto
import com.example.closedcircuitapplication.common.authentication.data.dataDto.RegisterResponseDto
import com.example.closedcircuitapplication.common.authentication.domain.models.*
import com.example.closedcircuitapplication.common.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.common.data.network.models.ResetPasswordDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.data.network.models.VerifyOtpDto
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.VerifyOtpRequest
import com.example.closedcircuitapplication.common.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestAuthRepository : AuthRepository {

    private var shouldReturnNetworkError = false
    var token = "737f8kn)u38ewo"
    var userId = "32ehykd7893j"
    var resourceMessage = ""
    var resourceError = ""

    val registerResponse = listOf<String>("benjamin@sgmail.com", "benjamin", "Beneficiary", "07012345678")
    val email = "benjamin@sgmail.com"
    val name = "benjamin"
    val roles = "Beneficiary"
    val phone_number = "07012345678"


    override suspend fun login(loginRequest: LoginRequest): Flow<Resource<Result<LoginResponseDto>>> =
        flow {
            emit(Resource.Loading())

            if (shouldReturnNetworkError) {
                emit(Resource.Error(resourceError, null))
            } else {
                emit(
                    Resource.Success(
                        Result(
                            resourceMessage,
                            LoginResponseDto(token, userId),
                            resourceError
                        )
                    )
                )
            }
        }

    override suspend fun register(registerRequest: RegisterRequest): Flow<Resource<Result<RegisterResponseDto>>> =
        flow {
            emit(Resource.Loading())

            if (shouldReturnNetworkError) {
                emit(Resource.Error(resourceError, null))
            }
            else {
                emit(
                    Resource.Success(
                        Result(
                            resourceMessage, RegisterResponseDto(
                                registerRequest.email,
                                registerRequest.fullname,
                                "Beneficiary",
                                registerRequest.phone_number
                            ), resourceError
                        )
                    )
                )
            }
        }

    override suspend fun generateOtp(generateOtpRequest: GenerateOtpRequest): Flow<Resource<Result<GenerateOtpDto>>> {
        TODO("Not yet implemented")
    }

    override suspend fun verifyOtp(verifyOtpRequest: VerifyOtpRequest): Flow<Resource<Result<VerifyOtpDto>>> {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Flow<Resource<Result<ResetPasswordDto>>> {
        TODO("Not yet implemented")
    }

    fun shouldReturnError(value: Boolean, errorMessage: String) {
        shouldReturnNetworkError = value
        resourceError = errorMessage
    }


}