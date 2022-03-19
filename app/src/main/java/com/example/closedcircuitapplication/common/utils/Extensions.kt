package com.example.closedcircuitapplication.common.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.compose.ui.text.toLowerCase
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.presentation.ui.MainActivity
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
