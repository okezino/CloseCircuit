package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.common.common.data.network.models.CreatePlanDto
import com.example.closedcircuitapplication.common.common.data.network.models.Result
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.plan.domain.usecases.CreatePlanUseCase
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.CreatePlanRequest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreatePlanViewModel @Inject constructor(private val createPlanUseCase: CreatePlanUseCase): ViewModel() {

    private var _createPlanResponse = MutableLiveData<Resource<Result<CreatePlanDto>>>()
    val createPlanResponse: LiveData<Resource<Result<CreatePlanDto>>> get() = _createPlanResponse

    fun createPlan(createPlanRequest: CreatePlanRequest) {
        viewModelScope.launch {
            createPlanUseCase(createPlanRequest).collect {
                _createPlanResponse.value = it
            }
        }
    }

}