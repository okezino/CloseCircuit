package com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.data

data class PlansFunded(
    val planFundedImage:Int,
    val planSector : String,
    val amountFunded :String,
    val fundingType : String,
    val fundRaised : Int,
    val taskCompleted:Int
)
