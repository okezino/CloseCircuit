package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.common.common.data.network.models.*
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.data.datadto.DeletePlanResponseDto
import com.example.closedcircuitapplication.beneficiary.plan.data.datadto.UpdatePlanResponseDto
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.UpdatePlanRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.VerifyOtpRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.usecases.*
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.CreatePlanRequest
import com.example.closedcircuitapplication.common.common.data.network.models.GenerateOtpDto
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.GetMyPlansDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanViewModel @Inject constructor(
    private val generateOtpUseCase: GenerateOtpUseCase,
    private val verifyOtpUseCase: VerifyOtpUseCase,
    private val  deletePlanUseCases: DeletePlanUseCaases,
    private val createPlanUseCase: CreatePlanUseCase,
    private val getPlanUseCase: GetPlanUseCase,
    private val updatePlanUseCase: UpdatePlanUseCase,
    private val getMyPlansUseCase: GetMyPlansUseCase,
    private val getUserStepsUseCase: GetUserStepsUseCase
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

    private var _updatePlanResponse = MutableLiveData<Resource<Result<UpdatePlanResponseDto>>>()
    val updatePlanResponse: LiveData<Resource<Result<UpdatePlanResponseDto>>> get() = _updatePlanResponse

    private var _getMyPlansResponse = MutableLiveData<Resource<Result<GetMyPlansDto>>>()
    val getMyPlansResponse: LiveData<Resource<Result<GetMyPlansDto>>> get() = _getMyPlansResponse

    private var _getStepsResponse = MutableLiveData<Resource<Result<GetStepsDto>>>()
    val getStepsResponse: LiveData<Resource<Result<GetStepsDto>>> get() = _getStepsResponse



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

    fun createPlan(createPlanRequest: CreatePlanRequest) {
        viewModelScope.launch {
            createPlanUseCase(createPlanRequest).collect {
                _createPlanResponse.value = it
            }
        }
    }

    fun getPlan(planId: String){
        viewModelScope.launch {
            getPlanUseCase(planId).collect{
                _getPlanResponse.value = it
            }
        }
    }

    fun deletePlan(id: String){
        viewModelScope.launch {
            deletePlanUseCases(id).collect {
                _deletePlanResponse.value = it
            }
        }
    }

    fun updateUserPlan(updatePlanRequest: UpdatePlanRequest, planId: String){
        viewModelScope.launch {
            updatePlanUseCase(updatePlanRequest, planId).collect {
                _updatePlanResponse.value = it
            }
        }
    }

    fun getMyPlans(limit: Int, offset: Int){
        viewModelScope.launch {
            getMyPlansUseCase(limit, offset).collect {
                _getMyPlansResponse.value = it
            }
        }
    }

    fun getUserSteps() {
        viewModelScope.launch {
            getUserStepsUseCase().collect {
                _getStepsResponse.value = it
            }
        }
    }
}