package com.example.closedcircuitapplication.plan.presentation.ui.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.common.data.network.models.CreateBudgetDto
import com.example.closedcircuitapplication.common.data.network.models.CreateStepDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.makeSnackBar
import com.example.closedcircuitapplication.plan.domain.models.CreateBudgetRequest
import com.example.closedcircuitapplication.plan.domain.models.CreateStepsRequest
import com.example.closedcircuitapplication.plan.domain.usecases.CreateBudgetUseCase
import com.example.closedcircuitapplication.plan.domain.usecases.CreateStepUseCase
import com.example.closedcircuitapplication.plan.presentation.models.AddBudget
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StepsViewModel @Inject constructor(
    val createStepUseCase: CreateStepUseCase,
    val createBudgetUseCase: CreateBudgetUseCase
) :
    ViewModel() {

    private var _createStepResponse: MutableLiveData<Resource<Result<CreateStepDto>>> =
        MutableLiveData()
    val createStepResponse: LiveData<Resource<Result<CreateStepDto>>> get()  = _createStepResponse
    private var _createBudgetResponse: MutableLiveData<Resource<Result<CreateBudgetDto>>> = MutableLiveData()
    val createBudgetResponse: LiveData<Resource<Result<CreateBudgetDto>>> get() = _createBudgetResponse

    private var _budgetListLiveData: MutableLiveData<ArrayList<AddBudget>> = MutableLiveData()
    val budgetListLiveData: LiveData<ArrayList<AddBudget>> get() = _budgetListLiveData

    private var budgetList: ArrayList<AddBudget> = arrayListOf()

    fun addToBudgetList(budgetItem: AddBudget) {
        this.budgetList.add(budgetItem)
        this._budgetListLiveData.value = budgetList
    }

    fun deleteFromBudgetList(budgetItem: AddBudget) {
        budgetList.remove(budgetItem)
        _budgetListLiveData.value = budgetList
    }

    fun getBudgetList() = budgetList


    fun createStep(createStepsRequest: CreateStepsRequest, authHeader: String) {
        viewModelScope.launch {
            createStepUseCase(createStepsRequest, authHeader).collect {
                _createStepResponse.value = it
            }
        }
    }

    fun createBudget(createBudgetRequest: CreateBudgetRequest,authHeader: String ) {
        viewModelScope.launch {
            createBudgetUseCase(createBudgetRequest, authHeader).collect {
                _createBudgetResponse.value = it
            }
        }
    }

    fun validateCreateStepsFields(
        stepName: String,
        stepDescription: String,
        stepDuration: String,
        context: Fragment
    ): Boolean {
        if (stepName.isNotEmpty() && stepDescription.isNotEmpty() && stepDescription.isNotEmpty()){
            return true
        }
        else if (stepName.isEmpty()){
            context.view?.let { context.makeSnackBar("Step name must be filled", it) }
            return false
        }
        else if (stepDescription.isEmpty()){
            context.view?.let { context.makeSnackBar("Step description must be filled", it) }
            return false
        }
        else if (stepDuration.isEmpty()){
            context.view?.let { context.makeSnackBar("Step duration must be filled", it) }
        }
        return false
    }
}