package com.example.closedcircuitapplication.authentication.domain.usecases

import com.example.closedcircuitapplication.common.domain.repository.TestAuthRepository
import org.junit.Before


class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase
    private var fakeAuthRepository = TestAuthRepository()

    @Before
    fun setUp() {
        loginUseCase = LoginUseCase(fakeAuthRepository)
    }


}