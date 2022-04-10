package com.example.closedcircuitapplication.common.authentication.domain.models

import com.example.closedcircuitapplication.common.authentication.presentation.models.UiLoginReponse

data class LoginResponse(
    val token : String,
    val userId: String
){
    fun toUi():UiLoginReponse{
        return UiLoginReponse(
            token = token, userId
        )
    }
}
