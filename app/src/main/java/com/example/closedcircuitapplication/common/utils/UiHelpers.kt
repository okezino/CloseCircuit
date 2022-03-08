package com.example.closedcircuitapplication.common.utils

import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.presentation.utils.showCustomViewDialog
import com.example.closedcircuitapplication.databinding.LogoutDialogLayoutBinding
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

    return showCustomDialog(
        requireContext(), resources,
        R.layout.custom_login_wait_dialog,
        false
    )
}

fun showCustomDialog(
    context: Context,
    resources: Resources,
    layout: Int,
    cancelable: Boolean = true
): AlertDialog {
    val view = View.inflate(context, layout, null)
    val builder = AlertDialog.Builder(context)
    builder.setView(view)
    builder.setCancelable(cancelable)
    val dialog = builder.create()
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    val width = (resources.displayMetrics.widthPixels * 0.80).toInt()
    val height = (resources.displayMetrics.heightPixels * 0.35).toInt()
    dialog!!.window?.setLayout(width, height)

    return dialog
}

fun showLogOutDialog(context: Context, binding: LogoutDialogLayoutBinding, resources: Resources, logOutFunction: () -> Unit): AlertDialog {
    val builder = AlertDialog.Builder(context)
    builder.setView(binding.root)
    builder.setCancelable(false)
    val dialog = builder.create()
    val width = (resources.displayMetrics.widthPixels * 0.80).toInt()
    val height = (resources.displayMetrics.heightPixels * 0.35).toInt()
    dialog!!.window?.setLayout(width, height)

    binding.logoutDialogYesTextView.setOnClickListener {
        logOutFunction.invoke()
        dialog.dismiss()
    }
    binding.logoutDialogNoTextView.setOnClickListener {
        dialog.dismiss()
    }
    return dialog
}

