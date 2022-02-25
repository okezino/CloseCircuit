package com.example.closedcircuitapplication.dashboard.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.common.data.network.models.GetUserDetailsDto
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.dashboard.domain.GetUserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewmodel @Inject constructor(
    private var getUserDetailsUseCase: GetUserDetailsUseCase
): ViewModel() {

    private var _getUserDetailsResponse = MutableLiveData<Resource<Result<GetUserDetailsDto>>>()
    val getUserDetailsResponse: LiveData<Resource<Result<GetUserDetailsDto>>> get() = _getUserDetailsResponse

    fun getUserDetails(userId: String, authHeader: String) {
        viewModelScope.launch {
           getUserDetailsUseCase(userId, authHeader).collect {
               _getUserDetailsResponse.value = it
           }
        }
    }

}