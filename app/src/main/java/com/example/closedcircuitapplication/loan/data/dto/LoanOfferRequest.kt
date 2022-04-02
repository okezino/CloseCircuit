package com.example.closedcircuitapplication.loan.data.dto

data class LoanOfferRequest(
    val projectName :String,
    val numberOfSponsor :String,
    val totalAmount: String,
    val sponsorImage : ArrayList<Int>
)
