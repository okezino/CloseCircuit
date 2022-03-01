package com.example.closedcircuitapplication.common.utils

import android.app.AlertDialog
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.presentation.utils.showCustomViewDialog
import com.google.android.material.snackbar.Snackbar

// this is used for navigation animation
fun Fragment.customNavAnimation(): NavOptions.Builder {
    val navBuilder: NavOptions.Builder = NavOptions.Builder()
    navBuilder.setEnterAnim(R.anim.slide_in_right).setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left).setPopExitAnim(R.anim.slide_out_right)
    return navBuilder
}

fun Fragment.makeSnackBar(message: String, view: View) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        .setBackgroundTint(resources.getColor(R.color.text_color))
        .setTextColor(resources.getColor(R.color.white))
        .show()
}

fun Fragment.showPleaseWaitAlertDialog(): AlertDialog {
    return showCustomViewDialog(requireContext(), resources, R.layout.custom_login_wait_dialog, false)
}