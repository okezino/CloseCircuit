package com.example.closedcircuitapplication.authentication.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.closedcircuitapplication.authentication.domain.models.Posts
import com.example.closedcircuitapplication.authentication.domain.usecases.AuthenticationUseCase
import com.example.closedcircuitapplication.common.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PostsViewModel @Inject constructor(private val  useCase: AuthenticationUseCase): ViewModel() {
    private var _postState = MutableLiveData<Resource<MutableList<Posts>>>()
    val postState: LiveData<Resource<MutableList<Posts>>> get() = _postState

    init {
        getPosts()
    }

   private fun getPosts() {
        viewModelScope.launch {
            _postState.value = useCase.invoke()
        }

    }
}