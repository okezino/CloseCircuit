package com.example.closedcircuitapplication.plan.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.common.data.network.models.Budget
import com.example.closedcircuitapplication.common.data.network.models.GetBudgetsDto
import com.example.closedcircuitapplication.common.data.network.models.GetStepsDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.domain.usecases.GetUserBudgetsUseCase
import com.example.closedcircuitapplication.plan.domain.usecases.GetUserStepsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllStepsViewModel @Inject constructor(
    private val getUserStepsUseCase: GetUserStepsUseCase,
    private val getUserBudgetsUseCase: GetUserBudgetsUseCase
) : ViewModel() {

    private var _getStepsResponse = MutableLiveData<Resource<Result<GetStepsDto>>>()
    val getStepsResponse: LiveData<Resource<Result<GetStepsDto>>> get() = _getStepsResponse

    private var _getBudgetsResponse = MutableLiveData<Resource<Result<GetBudgetsDto>>>()
    val getBudgetResponse: LiveData<Resource<Result<GetBudgetsDto>>> get() = _getBudgetsResponse

    var budgetsForPlan = arrayListOf<Budget>()
    var budgetsForStep = arrayListOf<Budget>()

    var stepId: String = ""
    var planId: String = ""

    fun getUserSteps(authHeader: String) {
        viewModelScope.launch {
            getUserStepsUseCase(authHeader).collect {
                _getStepsResponse.value = it
            }
        }
    }

    fun getUserBudgets(authHeader: String) {
        viewModelScope.launch {
            getUserBudgetsUseCase(authHeader).collect {
                _getBudgetsResponse.value = it
            }
        }
    }
}