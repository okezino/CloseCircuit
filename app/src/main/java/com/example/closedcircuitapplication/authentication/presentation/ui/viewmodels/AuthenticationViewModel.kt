package com.example.closedcircuitapplication.authentication.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.authentication.data.datadto.LoginResponseDto
import com.example.closedcircuitapplication.authentication.data.datadto.RegisterResponseDto
import com.example.closedcircuitapplication.authentication.domain.models.LoginRequest
import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest
import com.example.closedcircuitapplication.authentication.domain.usecases.LoginUseCase
import com.example.closedcircuitapplication.authentication.domain.usecases.RegisterUseCase
import com.example.closedcircuitapplication.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.closedcircuitapplication.common.data.network.models.Result

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private var _loginResponse = MutableLiveData<Resource<Result<LoginResponseDto>>>()
    val loginResponse: LiveData<Resource<Result<LoginResponseDto>>> get() = _loginResponse

    private val _registerResponse = MutableLiveData<Resource<Result<RegisterResponseDto>>>()
    val registerResponse : LiveData<Resource<Result<RegisterResponseDto>>> get() = _registerResponse

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            loginUseCase(loginRequest).collect {
                _loginResponse.value = it
            }
        }
    }

    fun register( registerRequest: RegisterRequest){
        viewModelScope.launch {
            registerUseCase(registerRequest).collect{
                _registerResponse.value = it
            }
        }
    }

}