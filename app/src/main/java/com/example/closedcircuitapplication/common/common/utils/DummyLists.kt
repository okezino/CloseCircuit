package com.example.closedcircuitapplication.utils

import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.beneficiary.loan.data.dto.LoanOfferDetailDto
import com.example.closedcircuitapplication.beneficiary.loan.data.dto.LoanOfferRequest
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.SponsorsItem
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.StepsBudgetItem

val budgetList = arrayListOf<StepsBudgetItem>(
    StepsBudgetItem("UI/UX Design", "NGN 100,000.00", "NGN 0"),
    StepsBudgetItem("Frontend Development", "NGN 200,000.00", "NGN 0"),
    StepsBudgetItem("Backend Development", "NGN 200,000.00", "NGN 0")
)

val sponsorsList = arrayListOf<SponsorsItem>(
    SponsorsItem("Marvin McKinney", "Loan", "NGN 200,00.00", true),
    SponsorsItem("Robert Fox", "Donation", "NGN 10,000.00", false))


    val loanOfferList = arrayListOf(
        LoanOfferDetailDto("Marvin McKinney", "30,000", "3", "20%", R.drawable.chat_profile_iv),
LoanOfferDetailDto("Gift Ben", "30,000", "3", "10%", R.drawable.customer_chat_image),
LoanOfferDetailDto("Kate Donner", "30,000", "3", "5%", R.drawable.sponsors_screen_image))

val loanItemList = arrayListOf(
    LoanOfferRequest("Web Application","20 Sponsors", "NGN 30,000", arrayListOf(
        R.drawable.chat_sender_image, R.drawable.customer_chat_image, R.drawable.customer_chat_image,
        R.drawable.diane_russell,
        R.drawable.diane_russell,
        R.drawable.diane_russell,
        R.drawable.diane_russell)),
    LoanOfferRequest("Mobile Application","40 Sponsors", "NGN 30,000", arrayListOf(R.drawable.customer_chat_image, R.drawable.diane_russell)),
)