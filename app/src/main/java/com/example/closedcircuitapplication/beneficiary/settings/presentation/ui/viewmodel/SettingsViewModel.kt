package com.example.closedcircuitapplication.beneficiary.settings.presentation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.common.common.utils.ChangePasswordResponseType
import com.example.closedcircuitapplication.beneficiary.settings.domain.models.ChangePasswordRequest
import com.example.closedcircuitapplication.beneficiary.settings.domain.usecases.ChangePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase

): ViewModel() {

    private var _changePasswordResponse = MutableLiveData<ChangePasswordResponseType>()
    val changePasswordResponse: LiveData<ChangePasswordResponseType> get() = _changePasswordResponse

    fun changePassword(changePasswordRequest: ChangePasswordRequest, userId: String){
        viewModelScope.launch {
            changePasswordUseCase(changePasswordRequest, userId).collect {
                _changePasswordResponse.value = it
            }
        }
    }
}