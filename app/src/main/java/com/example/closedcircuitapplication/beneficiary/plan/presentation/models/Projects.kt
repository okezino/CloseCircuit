package com.example.closedcircuitapplication.beneficiary.plan.presentation.models

data class Projects(
    val projectName: String,
    val projectDescription: String,
    var tasksCompleted: String,
    var fundsRaised: String,
    val planDuration: String,
    val planCategory: String
)
