package com.example.closedcircuitapplication.utils

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.closedcircuitapplication.MainActivity
import com.example.closedcircuitapplication.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView

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
