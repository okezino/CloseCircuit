package com.example.closedcircuitapplication.common.data.network

object ClosedCircuitApiEndpoints {
    const val LOGIN = "login/"
    const val REGISTER = "register/"
    const val DELETE_PLAN = "plans/{id}"
    const val SPONSORS = "sponsors/"
    const val GENERATE_OTP = "generate-otp/"
    const val VERIFY_OTP = "verify-otp/"
    const val RESET_PASSWORD = "reset-password/"
    const val UPDATE_PLAN = "plans/{id}/"
    const val PLANS = "plans/{userId}"
    const val USER_DETAILS = "manage-user/{id}/"
    const val GET_MY_PLANS = "plans/get-plans/"
    const val UPDATE_PROFILE = "manage-user/{id}/"
    const val CHANGE_PASSWORD = "change-password/{id}/"
    const val PLAN = "plans/"
}