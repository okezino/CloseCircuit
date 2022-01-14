package com.example.closedcircuitapplication.plan.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreatePlanObject(
    val category: String = "",
    val sector: String = ""
): Parcelable