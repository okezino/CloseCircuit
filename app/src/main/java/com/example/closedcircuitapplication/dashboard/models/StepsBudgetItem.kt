package com.example.closedcircuitapplication.dashboard.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StepsBudgetItem(
    val itemTitle: String,
    val targetFunds: String,
    val totalFundsRaised: String
): Parcelable
