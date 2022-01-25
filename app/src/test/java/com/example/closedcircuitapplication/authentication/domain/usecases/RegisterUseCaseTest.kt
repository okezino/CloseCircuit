package com.example.closedcircuitapplication.authentication.domain.usecases

import com.example.closedcircuitapplication.authentication.data.datadto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.domain.repository.TestAuthRepository
import com.example.closedcircuitapplication.common.utils.Resource
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class RegisterUseCaseTest {
    private lateinit var registerUseCase: RegisterUseCase
    private lateinit var fakeAuthRepository: TestAuthRepository
    private val email = "abdul@gmail.com"
    private val password = "Password123#"
    private val name = "Abdulqohar Salawu"
    private val role = "Beneficiary"
    private val confirm_password = "Password123#"
    private val phoneNumber = "08017654387"

    @Before
    fun setUp() {
        fakeAuthRepository = TestAuthRepository()
        registerUseCase = RegisterUseCase(fakeAuthRepository)
    }

    @Test
    fun `Flow emits loading first`() = runBlocking {
        val registerRequest =
            RegisterRequest(email, name, role, phoneNumber, password, confirm_password)
        val registerResponse = registerUseCase(registerRequest).first()
        Truth.assertThat(registerResponse).isEqualTo(Resource.Loading(null))
    }

    @Test
    fun `Register with correct details returns success`() = runBlocking {
        val registerRequest =
            RegisterRequest(email, name, role, phoneNumber, password, confirm_password)
        fakeAuthRepository.shouldReturnError(false, "")
        val registerResponse = registerUseCase(registerRequest).last()
        val registerResult = Result(
            fakeAuthRepository.resourceMessage,
            fakeAuthRepository.successData,
            fakeAuthRepository.resourceError
        )
        Truth.assertThat(registerResponse).isEqualTo(Resource.Success(registerResult))
    }

    @Test
    fun `Register with incorrect details returns error`() = runBlocking {
        val errorMessage = "User with email already exists"
        fakeAuthRepository.shouldReturnError(true, errorMessage)

        val registerRequest =
            RegisterRequest(email, name, role, phoneNumber, password, confirm_password)
        val registerResponse = registerUseCase(registerRequest).last()
        Truth.assertThat(registerResponse).isEqualTo(Resource.Error(errorMessage, null))
    }

    @Test
    fun `Register with preexisting email returns error`() = runBlocking {
        var userAlreadyExistsMessage = "User with email already exists"
        fakeAuthRepository.shouldReturnError(true, userAlreadyExistsMessage)

        val registerRequest =
            RegisterRequest(
                "kunleabiodun@gmail.com",
                name,
                role,
                phoneNumber,
                password,
                confirm_password
            )
        val registerResponse = registerUseCase(registerRequest).last()
        Truth.assertThat(registerResponse).isEqualTo(Resource.Error(userAlreadyExistsMessage, null))
    }
}