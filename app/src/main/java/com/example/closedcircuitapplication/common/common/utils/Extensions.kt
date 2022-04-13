package com.example.closedcircuitapplication.common.common.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextUtils
import android.util.Patterns
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.presentation.ui.MainActivity
import com.example.closedcircuitapplication.databinding.DeletePlanDialogBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

fun String.toEditable() = Editable.Factory.getInstance().newEditable(this)

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.remove() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun Boolean.toInteger() = if (this) 1 else 0

fun Int.toBoolean() = this == 1

fun BottomNavigationView.show() {
    this.isVisible = true
}

fun BottomNavigationView.hide() {
    this.isVisible = false
}

fun Fragment.hideBottomNavigationView() {
    requireActivity()
        .findViewById<BottomAppBar>(R.id.bottom_app_bar)
        .isVisible = false
}

fun Fragment.hideAppBar() {
    requireActivity()
        .findViewById<AppBarLayout>(R.id.appBarLayout)
        .isVisible = false
}

fun Fragment.hideFab() {
    requireActivity()
        .findViewById<BottomNavigationView>(R.id.fab)
        .isVisible = false
}

fun Context.closeSoftKeyboard(v: View) {
    val iMm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    iMm.hideSoftInputFromWindow(v.windowToken, 0)
    v.clearFocus()
}

interface OTPImpl {
    fun setResult(otp: String)
}

fun Activity.hideKeyboard() {
    try {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (this.currentFocus != null)
            imm.hideSoftInputFromWindow(currentFocus?.applicationWindowToken, 0)
    } catch (exc: Exception) {
        exc.printStackTrace()
    }
}

fun Activity.manipulateToolbar() {
    val activity = this as MainActivity
    if (activity.supportActionBar != null) {
        activity.supportActionBar?.setDisplayShowHomeEnabled(false)
        activity.supportActionBar?.setHomeButtonEnabled(false)
        activity.supportActionBar?.hide()
    }
}

fun String.isValidEmail() =
    !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()


fun Fragment.capitalizeWords(stringInput: String): String {
    val stringInputArray = stringInput.split(" ").toMutableList()
    var result = ""
    for (word in stringInputArray) {
        val lowercaseString = word.lowercase(Locale.getDefault())
        result += "${lowercaseString.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }} "
    }

    return result.trim()
}
fun Fragment.deletePlanAlertDialog(dialogBinding:DeletePlanDialogBinding, planName:TextView, deletePlan:()-> Unit):Dialog {
    val dialog = Dialog(requireContext()).apply {
        setContentView(dialogBinding.root)
        setCancelable(true)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    dialogBinding.deleteDialogTv.text = getString(R.string.deletePlan_dialog_message, planName.text.toString())

    dialogBinding.deletePlanNoBtn.setOnClickListener {
        Toast.makeText(requireContext(), "Action declined", Toast.LENGTH_SHORT).show()
        dialog.dismiss()
    }
    dialogBinding.deletePlanYesBtn.setOnClickListener { deletePlan.invoke()
        dialog.dismiss()
    }
    return dialog
}

fun Fragment.popBackStack(){
    findNavController().popBackStack()
}

fun Fragment.handleBackPress(){
    activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            popBackStack()
        }
    })
}

fun Fragment.setProgressDialog(context:Context, message:String): AlertDialog {
    val llPadding = 30
    val ll = LinearLayout(context)
    ll.orientation = LinearLayout.HORIZONTAL
    ll.setPadding(llPadding, llPadding, llPadding, llPadding)
    ll.gravity = Gravity.CENTER
    var llParam = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT)
    llParam.gravity = Gravity.CENTER
    ll.layoutParams = llParam

    val progressBar = ProgressBar(context)
    progressBar.isIndeterminate = true
    progressBar.setPadding(0, 0, llPadding, 0)
    progressBar.layoutParams = llParam

    llParam = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT)
    llParam.gravity = Gravity.CENTER
    val tvText = TextView(context)
    tvText.text = message
    tvText.setTextColor(Color.parseColor("#000000"))
    tvText.textSize = 20.toFloat()
    tvText.layoutParams = llParam

    ll.addView(progressBar)
    ll.addView(tvText)

    val builder = AlertDialog.Builder(context)
    builder.setCancelable(true)
    builder.setView(ll)

    val dialog = builder.create()
    val window = dialog.window
    if (window != null) {
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window?.attributes)
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
        dialog.window?.attributes = layoutParams
    }
    return dialog
}

