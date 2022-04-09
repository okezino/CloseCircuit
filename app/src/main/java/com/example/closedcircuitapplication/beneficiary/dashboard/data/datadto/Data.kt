package com.example.closedcircuitapplication.beneficiary.dashboard.data.datadto

data class Data(
    val country: String,
    val created_at: String,
    val email: String,
    val fullname: String,
    val id: String,
    val is_beneficiary: Boolean,
    val is_sponsor: Boolean,
    val is_verified: Boolean,
    val phone_number: String,
    val updated_at: String
)