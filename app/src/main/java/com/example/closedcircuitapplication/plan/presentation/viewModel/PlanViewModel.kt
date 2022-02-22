package com.example.closedcircuitapplication.plan.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.network.models.VerifyOtpDto
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.data.datadto.UpdatePlanResponseDto
import com.example.closedcircuitapplication.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.plan.domain.models.UpdatePlanRequest
import com.example.closedcircuitapplication.plan.domain.models.VerifyOtpRequest
import com.example.closedcircuitapplication.plan.domain.usecases.GenerateOtpUseCase
import com.example.closedcircuitapplication.plan.domain.usecases.UpdatePlanUseCase
import com.example.closedcircuitapplication.plan.domain.usecases.VerifyOtpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanViewModel @Inject constructor(
    private val generateOtpUseCase: GenerateOtpUseCase,
    private val verifyOtpUseCase: VerifyOtpUseCase,
    private val updatePlanUseCase: UpdatePlanUseCase
): ViewModel() {
    private var _generateOtpResponse = MutableLiveData<Resource<Result<GenerateOtpDto>>>()
    val generateOtpResponse: LiveData<Resource<Result<GenerateOtpDto>>> get() = _generateOtpResponse

    private var _verifyOtpResponse = MutableLiveData<Resource<Result<VerifyOtpDto>>>()
    val verifyOtpResponse: LiveData<Resource<Result<VerifyOtpDto>>> get() = _verifyOtpResponse

    private var _updatePlanResponse = MutableLiveData<Resource<Result<UpdatePlanResponseDto>>>()
    val updatePlanResponse: LiveData<Resource<Result<UpdatePlanResponseDto>>> get() = _updatePlanResponse

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

    fun updateUserPlan(updatePlanRequest: UpdatePlanRequest, planId: String, token: String){
        viewModelScope.launch {
            updatePlanUseCase(updatePlanRequest, planId, token).collect {
                _updatePlanResponse.value = it
            }
        }
    }
}