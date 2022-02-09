package com.example.closedcircuitapplication.authentication.presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.authentication.data.datadto.LoginResponseDto
import com.example.closedcircuitapplication.authentication.data.datadto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.*
import com.example.closedcircuitapplication.authentication.domain.usecases.*
import com.example.closedcircuitapplication.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.data.network.models.ResetPasswordDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.network.models.VerifyOtpDto
import com.example.closedcircuitapplication.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val generateOtpUseCase: GenerateOtpUseCase,
    private val verifyOtpUseCase: VerifyOtpUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    private var _loginResponse = MutableLiveData<Resource<Result<LoginResponseDto>>>()
    val loginResult: LiveData<Resource<Result<LoginResponseDto>>> get() = _loginResponse

    private var _registerResponse = MutableLiveData<Resource<Result<RegisterResponseDto>>>()
    val registerResult: LiveData<Resource<Result<RegisterResponseDto>>> get() = _registerResponse

    private var _generateOtpResponse = MutableLiveData<Resource<Result<GenerateOtpDto>>>()
    val generateOtpRequest: LiveData<Resource<Result<GenerateOtpDto>>> get() = _generateOtpResponse

    private var _verifyOtpResponse = MutableLiveData<Resource<Result<VerifyOtpDto>>>()
    val verifyOtpResponse: LiveData<Resource<Result<VerifyOtpDto>>> get() = _verifyOtpResponse

    private var _resetPasswordResponse = MutableLiveData<Resource<Result<ResetPasswordDto>>>()
    val resetPasswordResponse: LiveData<Resource<Result<ResetPasswordDto>>> get() = _resetPasswordResponse


    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            loginUseCase(loginRequest).collect {
                Log.d("token2", "msg2: ${it.datas?.data?.token}")
                _loginResponse.value = it
            }
        }
    }


    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            registerUseCase(registerRequest).collect {
                _registerResponse.value = it
            }
        }
    }


    fun generateOtp(generateOtpRequest: GenerateOtpRequest) {
        viewModelScope.launch {
            generateOtpUseCase(generateOtpRequest).collect{
                _generateOtpResponse.value = it
            }
        }
    }

    fun verifyOtp(verifyOtpRequest: VerifyOtpRequest) {
        viewModelScope.launch {
            verifyOtpUseCase(verifyOtpRequest).collect {
                _verifyOtpResponse.value = it
            }
        }
    }

    fun resetPassword(resetPasswordRequest: ResetPasswordRequest) {
        viewModelScope.launch {
            resetPasswordUseCase(resetPasswordRequest).collect {
                _resetPasswordResponse.value = it
            }
        }
    }
}
