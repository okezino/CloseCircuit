package com.example.closedcircuitapplication.dashboard.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.common.data.network.models.Result
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.dashboard.data.datadto.UserDetailsResponseDto
import com.example.closedcircuitapplication.dashboard.data.datadto.UserEditProfileResponseDto
import com.example.closedcircuitapplication.dashboard.domain.model.UpdateProfileRequest
import com.example.closedcircuitapplication.dashboard.domain.usecase.GetUserDetailsUseCase
import com.example.closedcircuitapplication.dashboard.domain.usecase.UpdateProfileUseCae
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val updateProfileUseCae: UpdateProfileUseCae
): ViewModel() {
    private var _userDetailsResponse = MutableLiveData<Resource<Result<UserDetailsResponseDto>>>()
    val userDetailsResponse: LiveData<Resource<Result<UserDetailsResponseDto>>> get() = _userDetailsResponse

    private var _updateProfileResponse = MutableLiveData<Resource<Result<UserEditProfileResponseDto>>>()
    val updateProfileResponse: LiveData<Resource<Result<UserEditProfileResponseDto>>> get() = _updateProfileResponse


    fun getUserDetails(userId: String, authHeader: String){
        viewModelScope.launch {
            getUserDetailsUseCase(userId, authHeader).collect {
                _userDetailsResponse.value = it
            }
        }
    }


    fun updateUserProfile(updateProfileRequest: UpdateProfileRequest, userId: String, token: String){
        viewModelScope.launch {
            updateProfileUseCae(updateProfileRequest, userId, token).collect {
                _updateProfileResponse.value = it
            }
        }
    }
}