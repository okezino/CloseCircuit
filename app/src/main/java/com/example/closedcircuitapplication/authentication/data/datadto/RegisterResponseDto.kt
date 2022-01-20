package com.example.closedcircuitapplication.authentication.data.datadto

import com.example.closedcircuitapplication.authentication.domain.models.RegisterRequest

data class RegisterResponseDto(
     val email : String,
     val name: String,
     val roles : String,
     val phone_number : String
 )
