package com.example.closedcircuitapplication.common.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import com.example.closedcircuitapplication.R
import com.google.android.material.snackbar.Snackbar

// this is used for navigation animation
fun Fragment.customNavAnimation(): NavOptions.Builder {
    val navBuilder: NavOptions.Builder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.slide_in_right).setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left).setPopExitAnim(R.anim.slide_out_right)
    return navBuilder
}

fun Fragment.makeSnackBar(message: String, view: View) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}