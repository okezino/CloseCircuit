package com.example.closedcircuitapplication.plan.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StepsBudgetItem(
    val itemTitle: String,
    val targetFunds: String,
    val totalFundsRaised: String
): Parcelable
