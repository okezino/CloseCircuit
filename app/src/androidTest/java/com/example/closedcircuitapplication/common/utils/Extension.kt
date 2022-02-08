package com.example.closedcircuitapplication.common.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import com.example.closedcircuitapplication.R

// this is used for navigation animation
fun customNavAnimation(): NavOptions.Builder {
    val navBuilder: NavOptions.Builder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.slide_in_right).setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left).setPopExitAnim(R.anim.slide_out_right)
    return navBuilder
}