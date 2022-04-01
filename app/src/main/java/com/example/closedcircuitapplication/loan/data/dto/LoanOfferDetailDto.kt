package com.example.closedcircuitapplication.loan.data.dto

data class LoanOfferDetailDto(
    val sponsorsName :String,
    val amount :String,
    val gracePeriod: String,
    val loanPercentage: String,
    val sponsorImage : Int
) {
}