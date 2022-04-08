package com.example.closedcircuitapplication.loan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.showAcceptLoanOfferAlertDialog
import com.example.closedcircuitapplication.common.utils.showDeclineLoanOfferAlertDialog
import com.example.closedcircuitapplication.databinding.AcceptLoanOfferDialogBinding
import com.example.closedcircuitapplication.databinding.DeclineOfferDialogBinding
import com.example.closedcircuitapplication.databinding.FragmentLoanDetailBinding


class LoanDetailFragment : Fragment() {

    private var _binding: FragmentLoanDetailBinding? = null
    private val binding get()   = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoanDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener(){
        acceptOfferClickListener()
        declineOfferClickListener()
    }

    private fun acceptOfferClickListener(){
        val acceptLoanOfferDialogBinding = AcceptLoanOfferDialogBinding.inflate(layoutInflater)
        val loanAmount = binding.loanAmountDetailValueTv.text.toString()
        val totalAmount =binding.totalRepaymentValueTv.text.toString()
        val acceptLoanOfferDialog = showAcceptLoanOfferAlertDialog(
            acceptLoanOfferDialogBinding, loanAmount, totalAmount
        ) { acceptLoanOffer() }
        binding.acceptLoanDetailBtn.setOnClickListener {
            acceptLoanOfferDialog.show()
        }
    }

    private fun declineOfferClickListener(){
        val declineLoanOfferDialogBinding = DeclineOfferDialogBinding.inflate(layoutInflater)
        val declineOfferDialog = showDeclineLoanOfferAlertDialog(
            declineLoanOfferDialogBinding
        ) { declineLoanOffer() }
        binding.declineLoanBtn.setOnClickListener {
            declineOfferDialog.show()
        }
    }

    private fun acceptLoanOffer(){
        //TODO(ACCEPT LOAN OFFER FUNCTION)

        Toast.makeText(requireContext(), "accepted successfully", Toast.LENGTH_SHORT).show()
    }

    private fun declineLoanOffer(){
        //TODO(decline LOAN OFFER FUNCTION)
        Toast.makeText(requireContext(), "offers declined", Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}