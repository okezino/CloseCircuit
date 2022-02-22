package com.example.closedcircuitapplication.authentication.domain.models

import com.example.closedcircuitapplication.authentication.presentation.models.UiLoginReponse

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
