package com.example.closedcircuitapplication.plan.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.common.data.network.models.CreatePlanDto
import com.example.closedcircuitapplication.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.data.network.models.VerifyOtpDto
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.data.datadto.DeletePlanResponseDto
import com.example.closedcircuitapplication.plan.domain.models.DeletePlanRequest
import com.example.closedcircuitapplication.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.plan.domain.models.VerifyOtpRequest
import com.example.closedcircuitapplication.plan.domain.usecases.DeletePlanUseCaases
import com.example.closedcircuitapplication.plan.domain.usecases.CreatePlanUseCase
import com.example.closedcircuitapplication.plan.domain.usecases.GenerateOtpUseCase
import com.example.closedcircuitapplication.plan.domain.usecases.GetPlanUseCase
import com.example.closedcircuitapplication.plan.domain.usecases.VerifyOtpUseCase
import com.example.closedcircuitapplication.plan.presentation.models.CreatePlanRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanViewModel @Inject constructor(
    private val generateOtpUseCase: GenerateOtpUseCase,
    private val verifyOtpUseCase: VerifyOtpUseCase,
    private val  deletePlanUseCaases: DeletePlanUseCaases,
    private val createPlanUseCase: CreatePlanUseCase,
    private val getPlanUseCase: GetPlanUseCase
): ViewModel() {
    private var _generateOtpResponse = MutableLiveData<Resource<Result<GenerateOtpDto>>>()
    val generateOtpResponse: LiveData<Resource<Result<GenerateOtpDto>>> get() = _generateOtpResponse

    private var _verifyOtpResponse = MutableLiveData<Resource<Result<VerifyOtpDto>>>()
    val verifyOtpResponse: LiveData<Resource<Result<VerifyOtpDto>>> get() = _verifyOtpResponse

    private var _createPlanResponse = MutableLiveData<Resource<Result<CreatePlanDto>>>()
    val createPlanResponse: LiveData<Resource<Result<CreatePlanDto>>> get() = _createPlanResponse

    private var _getPlanResponse = MutableLiveData<Resource<Result<CreatePlanDto>>>()
    val getPlanResponse: LiveData<Resource<Result<CreatePlanDto>>> get() = _getPlanResponse

    private var _deletePlanResponse = MutableLiveData<Resource<Result<DeletePlanResponseDto>>>()
    val deletePlanResponse :LiveData<Resource<Result<DeletePlanResponseDto>>> get() = _deletePlanResponse

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

    fun createPlan(createPlanRequest: CreatePlanRequest, authHeader: String) {
        viewModelScope.launch {
            createPlanUseCase(createPlanRequest, authHeader).collect {
                _createPlanResponse.value = it
            }
        }
    }

    fun getPlan(planId: String, authHeader: String){
        viewModelScope.launch {
            getPlanUseCase(planId, authHeader).collect{
                _getPlanResponse.value = it
            }
        }
    }

    fun deletePlan(id: String, authHeader:String){
        viewModelScope.launch {
            deletePlanUseCaases(id, authHeader).collect {
                _deletePlanResponse.value = it
            }
        }
    }
}