package com.example.closedcircuitapplication.utils

import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.beneficiary.loan.data.dto.LoanOfferDetailDto
import com.example.closedcircuitapplication.beneficiary.loan.data.dto.LoanOfferRequest
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.SponsorsItem
import com.example.closedcircuitapplication.beneficiary.plan.presentation.models.StepsBudgetItem
import com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.data.PlansFunded
import com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.model.SponsorPlanSummaryBudgetDto
import com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.model.SponsorPlanSummaryStepDto

val budgetList = arrayListOf<StepsBudgetItem>(
    StepsBudgetItem("UI/UX Design", "NGN 100,000.00", "NGN 0"),
    StepsBudgetItem("Frontend Development", "NGN 200,000.00", "NGN 0"),
    StepsBudgetItem("Backend Development", "NGN 200,000.00", "NGN 0"))

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

    val planFundedItem = arrayListOf(
    PlansFunded(R.drawable.customer_chat_image, "E-commerce(Electronics)", "NGN 20,000", "Loan", 40, 15),
    PlansFunded(R.drawable.diane_russell, "Project", "NGN 50,000", "Loan", 30, 10),
    PlansFunded(R.drawable.customer_chat_image, "School Fees", "NGN 90,000", "Loan", 90, 35)
    )

val sponsorPlanSummaryBudgetItem1 = arrayListOf(
    SponsorPlanSummaryBudgetDto("Budget item 1:","UI/UX Design","NGN 100,000"),
    SponsorPlanSummaryBudgetDto("Budget item 2:","UI/UX Design","NGN 200,000"),
    SponsorPlanSummaryBudgetDto("Budget item 3:","UI/UX Design","NGN 300,000"),
)
val sponsorPlanSummaryBudgetItem2 = arrayListOf(
    SponsorPlanSummaryBudgetDto("Budget item 4:","UI/UX Design","NGN 400,000"),
    SponsorPlanSummaryBudgetDto("Budget item 5:","UI/UX Design","NGN 500,000"),
    SponsorPlanSummaryBudgetDto("Budget item 6:","UI/UX Design","NGN 600,000"),
)

val sponsorPlanSummaryBudgetItem3 = arrayListOf(
    SponsorPlanSummaryBudgetDto("Budget item 7:","UI/UX Design","NGN 700,000"),
    SponsorPlanSummaryBudgetDto("Budget item 8:","UI/UX Design","NGN 800,000"),
)


val sponsorPlanSummaryStepItem = mutableListOf(
    SponsorPlanSummaryStepDto("step 1:","Buy a Land","1000,000", sponsorPlanSummaryBudgetItem1),
    SponsorPlanSummaryStepDto("step 2:","Build House","700,000", sponsorPlanSummaryBudgetItem2),
    SponsorPlanSummaryStepDto("step 3:","Buy a Television","100,000", sponsorPlanSummaryBudgetItem3),
)