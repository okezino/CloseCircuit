package com.example.closedcircuitapplication.authentication.domain.usecases

import com.example.closedcircuitapplication.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.common.domain.repository.TestAuthRepository
import com.example.closedcircuitapplication.common.utils.Resource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var fakeAuthRepository: TestAuthRepository
    private val email = "abdul@gmail.com"
    private val password = "Password123#"

    @Before
    fun setUp() {
        fakeAuthRepository = TestAuthRepository()
        loginUseCase = LoginUseCase(fakeAuthRepository)
    }

    @Test
    fun `Flow emits Loading Result first`() = runBlocking {
        val loginRequest = LoginRequest(email, password)
        val loginResponse = loginUseCase(loginRequest).first()

        assertThat(loginResponse).isEqualTo(Resource.Loading(null))
    }



    @Test
    fun `Login with Incorrect Details and Return Error`() = runBlocking {
        val errorMessage = "Incorrect email and password"
        fakeAuthRepository.shouldReturnError(true, errorMessage)

        val loginRequest = LoginRequest(email, password)
        val loginResponse = loginUseCase(loginRequest).last()

        assertThat(loginResponse).isEqualTo(Resource.Error(errorMessage, null))
    }


}