package com.example.closedcircuitapplication.common.common.presentation.utils

import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
import android.view.View


fun showCustomViewDialog(
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
    dialog.show()
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    val width = (resources.displayMetrics.widthPixels * 0.80).toInt()
    val height = (resources.displayMetrics.heightPixels * 0.35).toInt()
    dialog!!.window?.setLayout(width, height)

    return dialog
}