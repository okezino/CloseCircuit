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

import org.junit.After
import org.junit.Before
import org.junit.Test

class RegisterUseCaseTest {

    lateinit var registerUseCase: RegisterUseCase
    lateinit var fakeAuthRepository: TestAuthRepository


    val email = "benjamin@sgmail.com"
    val fullname = "benjamin"
    val roles = "Beneficiary"
    val phone_number = "+23407012345678"
    val password = "Password@123"
    val confirm_password = "Password@123"

    @Before
    fun setUp() {
        fakeAuthRepository = TestAuthRepository()
        registerUseCase = RegisterUseCase(fakeAuthRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    operator fun invoke() {


    }

    @Test
    fun `flow emit loading result first`() = runBlocking{
        val registerRequest = RegisterRequest(email, fullname, roles, phone_number,password, confirm_password)
        val registerReponse = registerUseCase(registerRequest).first()

        Truth.assertThat(registerReponse).isEqualTo(Resource.Loading(null))
    }

    @Test
    fun `flow emit success when registered with correct credentials`() = runBlocking{
        val registerRequest = RegisterRequest(email, fullname, roles, phone_number, password, confirm_password)
        val registerReponse = registerUseCase(registerRequest).last()

        val registerResult = Result(
            fakeAuthRepository.resourceMessage,
            RegisterResponseDto(email, fullname, roles, phone_number),
            fakeAuthRepository.resourceError
        )

        Truth.assertThat(registerReponse).isEqualTo(Resource.Success(registerResult))    }


    @Test
    fun `Register with Incorrect Details and Return Error`() = runBlocking {
        val errorMessage = "Incorrect email and password"
        fakeAuthRepository.shouldReturnError(true, errorMessage)

        val registerRequest = RegisterRequest(email, fullname, roles, "4",  "", confirm_password)
        val registerResponse = registerUseCase(registerRequest).last()

        Truth.assertThat(registerResponse).isEqualTo(Resource.Error(errorMessage, null))
    }
}