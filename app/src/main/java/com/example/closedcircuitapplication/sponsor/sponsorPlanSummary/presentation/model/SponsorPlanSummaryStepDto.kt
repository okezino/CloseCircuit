package com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.model

data class SponsorPlanSummaryStepDto(
    val step: String,
    val stepName: String,
    val stepCost: String,
    val budget: ArrayList<SponsorPlanSummaryBudgetDto>
)
