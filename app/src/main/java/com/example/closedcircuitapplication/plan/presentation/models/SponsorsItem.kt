package com.example.closedcircuitapplication.plan.presentation.models

data class SponsorsItem(
    var sponsorName: String,
    var sponsorshipType: String,
    var sponsorshipAmount: String,
    var eligibleApprover: Boolean
)