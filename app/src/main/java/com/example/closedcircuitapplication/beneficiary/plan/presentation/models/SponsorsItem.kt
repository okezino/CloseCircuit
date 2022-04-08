package com.example.closedcircuitapplication.beneficiary.plan.presentation.models

data class SponsorsItem(
    var sponsorName: String,
    var sponsorshipType: String,
    var sponsorshipAmount: String,
    var eligibleApprover: Boolean
)