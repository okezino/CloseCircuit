package com.example.closedcircuitapplication.beneficiary.loan.utils

interface OnItemClickListener {
    fun onItemClickListener(position:Int)
    fun onDeclineBtnClicked(position: Int)
    fun onAcceptBtnClicked(position: Int)
}