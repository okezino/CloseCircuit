package com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.closedcircuitapplication.sponsor.sponsorPlanSummary.data.PlansFunded

class SponsorsViewModel : ViewModel(){


    fun displayPlanFundedItem(planFundedItem:List<PlansFunded>):List<PlansFunded>{
        val fundItemList:ArrayList<PlansFunded> = arrayListOf()
        if (planFundedItem.size >=2 ){
            fundItemList.add(planFundedItem[0])
            fundItemList.add(planFundedItem[1])
            return fundItemList
        }
        return planFundedItem
    }
}