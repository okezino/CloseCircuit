package com.example.closedcircuitapplication.authentication.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.authentication.domain.models.Post
import com.example.closedcircuitapplication.authentication.domain.usecases.LoginUseCase
import com.example.closedcircuitapplication.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private var _postState = MutableLiveData<Resource<List<Post>>>()
    val postState: LiveData<Resource<List<Post>>> get() = _postState


    fun getPosts() {
        viewModelScope.launch {
            loginUseCase().collect {
                _postState.value = it
            }
        }

    }
}