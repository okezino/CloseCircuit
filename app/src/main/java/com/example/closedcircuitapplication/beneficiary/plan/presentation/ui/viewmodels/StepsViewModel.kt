package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.data.network.models.*
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.common.common.utils.makeSnackBar
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.CreateBudgetRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.CreateStepsRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.UpdateBudgetRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.models.UpdateStepRequest
import com.example.closedcircuitapplication.beneficiary.plan.domain.usecases.*
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.AddBudget
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StepsViewModel @Inject constructor(
    private val createStepUseCase: CreateStepUseCase,
    private val createBudgetUseCase: CreateBudgetUseCase,
    private val deleteStepUseCase: DeleteStepUseCase,
    private val updateStepUseCase: UpdateStepUseCase,
    private val deleteBudgetUseCase: DeleteBudgetUseCase,
    private val updateBudgetUseCase: UpdateBudgetUseCase
) :
    ViewModel() {

    private var _createStepResponse: MutableLiveData<Resource<Result<CreateStepDto>>> =
        MutableLiveData()
    val createStepResponse: LiveData<Resource<Result<CreateStepDto>>> get()  = _createStepResponse
    private var _createBudgetResponse: MutableLiveData<Resource<Result<CreateBudgetDto>>> = MutableLiveData()
    val createBudgetResponse: LiveData<Resource<Result<CreateBudgetDto>>> get() = _createBudgetResponse

    private var _budgetListLiveData: MutableLiveData<ArrayList<AddBudget>> = MutableLiveData()
    val budgetListLiveData: LiveData<ArrayList<AddBudget>> get() = _budgetListLiveData

    private var _deleteStepResponse: MutableLiveData<Resource<Result<String>>> = MutableLiveData()
    val deleteStepResponse: LiveData<Resource<Result<String>>> get() = _deleteStepResponse

    private var _updateStepsResponse = MutableLiveData<Resource<Result<UpdateStepDto>>>()
    val updateStepsResponse: LiveData<Resource<Result<UpdateStepDto>>> get() = _updateStepsResponse

    private var _deleteBudgetResponse: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val deleteBudgetResponse: LiveData<Resource<Unit>> get() = _deleteBudgetResponse

    private var _updateBudgetResponse: MutableLiveData<Resource<Result<UpdateBudgetDto>>> = MutableLiveData()
    val updateBudgetResponse: LiveData<Resource<Result<UpdateBudgetDto>>> get() = _updateBudgetResponse

    private var budgetList: ArrayList<AddBudget> = arrayListOf()
    var editBudgetMode: Boolean = false
    var budgetBeingEdited: Budget? = null
    var positionOfBudgetBeingEdited = 0
    var newBudgetItem: CreateBudgetRequest? = null

    fun addToBudgetList(budgetItem: AddBudget) {
        this.budgetList.add(budgetItem)
        this._budgetListLiveData.value = budgetList
    }

    fun deleteFromBudgetList(budgetItem: AddBudget) {
        budgetList.remove(budgetItem)
        _budgetListLiveData.value = budgetList
    }



    fun deleteStep(stepId: String) {
        viewModelScope.launch {
            deleteStepUseCase(stepId).collect {
                _deleteStepResponse.value = it
            }
        }
    }

    fun getBudgetList() = budgetList


    fun createStep(createStepsRequest: CreateStepsRequest) {
        viewModelScope.launch {
            createStepUseCase(createStepsRequest).collect {
                _createStepResponse.value = it
            }
        }
    }

    fun createBudget(createBudgetRequest: CreateBudgetRequest ) {
        viewModelScope.launch {
            createBudgetUseCase(createBudgetRequest).collect {
                _createBudgetResponse.value = it
            }
        }
    }

    fun updateStep(stepId:String,updateStepRequest: UpdateStepRequest) {
        viewModelScope.launch {
            updateStepUseCase(stepId, updateStepRequest).collect {
                _updateStepsResponse.value = it
            }
        }
    }

    fun deleteBudget(budgetId: String) {
        viewModelScope.launch {
            deleteBudgetUseCase(budgetId).collect {
                _deleteBudgetResponse.value = it
            }
        }
    }

    fun updateBudget(budgetId: String,  updateBudgetRequest: UpdateBudgetRequest) {
        viewModelScope.launch {
            updateBudgetUseCase(budgetId,updateBudgetRequest).collect {
                _updateBudgetResponse.value = it
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
            context.view?.let { context.makeSnackBar(context.getString(R.string.step_name_must_be_filled), it) }
            return false
        }
        else if (stepDescription.isEmpty()){
            context.view?.let { context.makeSnackBar(context.getString(R.string.description_must_be_filled), it) }
            return false
        }
        else if (stepDuration.isEmpty()){
            context.view?.let { context.makeSnackBar(context.getString(R.string.step_duration_must_be_filled), it) }
        }
        return false
    }
}