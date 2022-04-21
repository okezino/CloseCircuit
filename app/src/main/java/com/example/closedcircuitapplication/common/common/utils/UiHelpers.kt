package com.example.closedcircuitapplication.common.common.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.AcceptLoanOfferDialogBinding
import com.example.closedcircuitapplication.databinding.ActivityBeneficiaryDashboardBinding
import com.example.closedcircuitapplication.databinding.DeclineOfferDialogBinding
import com.example.closedcircuitapplication.databinding.LogoutDialogLayoutBinding
import com.example.closedcircuitapplication.beneficiary.loan.presentation.ui.screens.LoanDetailFragmentDirections
import com.example.closedcircuitapplication.common.common.presentation.ui.MainActivity
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

fun showToast(context: Context, string: String){
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}

 fun Activity.userLogOut(): Unit {
    val intent = Intent(this, MainActivity::class.java)
    intent.putExtra(FROM_LOGOUT, true)
    startActivity(intent)
    this.finish()
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


fun Fragment.showAcceptLoanOfferAlertDialog(dialogBinding: AcceptLoanOfferDialogBinding, loanAmount: String, totalAmount:String, acceptLoanOffer:()-> Unit): Dialog {
    val dialog = Dialog(requireContext()).apply {
        setContentView(dialogBinding.root)
        setCancelable(true)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    dialogBinding.acceptLoanSummaryDialogTv.text = getString(R.string.loan_summary, loanAmount, totalAmount)


    dialogBinding.acceptLoanOfferBtn.setOnClickListener {
        acceptLoanOffer.invoke()
        dialog.dismiss()
        findNavController().navigate(LoanDetailFragmentDirections.actionLoanDetailFragmentToLoanOfferFragment(), customNavAnimation().build())
    }
    return dialog
}

fun Fragment.showDeclineLoanOfferAlertDialog(dialogBinding: DeclineOfferDialogBinding, declineLoanOffer:()-> Unit): Dialog {
    val dialog = Dialog(requireContext()).apply {
        setContentView(dialogBinding.root)
        setCancelable(true)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    dialogBinding.sendMessageT0SponsorBtn.setOnClickListener {
        findNavController().navigate(LoanDetailFragmentDirections.actionLoanDetailFragmentToDeclineLoanOfferFragment(), customNavAnimation().build())
        dialog.dismiss()
    }
    dialogBinding.declineLoanOfferBtn.setOnClickListener {
        declineLoanOffer.invoke()
        dialog.dismiss()
        Toast.makeText(requireContext(), "Loan Declined", Toast.LENGTH_SHORT).show()
        findNavController().navigate(LoanDetailFragmentDirections.actionLoanDetailFragmentToLoanOfferFragment(), customNavAnimation().build())

    }
    return dialog
}

fun Activity.showToolbarHeader(binding: ActivityBeneficiaryDashboardBinding){
    binding.appBarDashboard.toolbarHeaderTitleTv.visibility= View.VISIBLE
    binding.appBarDashboard.toolbarHeaderTitleTv.text = "Loan offers"
    binding.appBarDashboard.profileImageView.visibility = View.INVISIBLE
    binding.appBarDashboard.notificationImageView.visibility = View.INVISIBLE
    binding.appBarDashboard.contentMain.bottomAppBar.visibility = View.GONE
    binding.appBarDashboard.contentMain.fab.visibility = View.GONE
}


