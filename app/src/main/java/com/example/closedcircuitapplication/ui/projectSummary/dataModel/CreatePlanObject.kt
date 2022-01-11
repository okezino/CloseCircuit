package com.example.closedcircuitapplication.ui.projectSummary.dataModel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CreatePlanObject(
    val category: String = "",
    val sector: String = ""
): Parcelable