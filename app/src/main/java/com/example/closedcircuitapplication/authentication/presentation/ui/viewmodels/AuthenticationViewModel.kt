package com.example.closedcircuitapplication.authentication.presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.authentication.data.dataDto.LoginResponseDto
import com.example.closedcircuitapplication.authentication.data.dataDto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.authentication.domain.usecases.LoginUseCase
import com.example.closedcircuitapplication.authentication.domain.usecases.RegisterUseCase
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    private var _loginResponse = MutableLiveData<Resource<Result<LoginResponseDto>>>()
    val loginResult: LiveData<Resource<Result<LoginResponseDto>>> get() = _loginResponse

    private var _registerResponse = MutableLiveData<Resource<Result<RegisterResponseDto>>>()
    val registerResult: LiveData<Resource<Result<RegisterResponseDto>>> get() = _registerResponse


    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            loginUseCase(loginRequest).collect {
                Log.d("token2", "msg2: ${it.data?.data?.token}")
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

}