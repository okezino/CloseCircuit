package com.example.closedcircuitapplication.beneficiary.loan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentDeclineLoanOfferBinding

class DeclineLoanOfferFragment : Fragment()  {

    private  var _binding : FragmentDeclineLoanOfferBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        _binding = FragmentDeclineLoanOfferBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        declineLoanOffer()
    }

    private fun declineLoanOffer(){
        //TODO( message to be sent to the user)
        val message = binding.declineMessageEt.text.toString()
        binding.declineLoanBtn.setOnClickListener {
            findNavController().navigate(DeclineLoanOfferFragmentDirections.actionDeclineLoanOfferFragmentToLoanOfferDetailsFragment(), customNavAnimation().build())
            Toast.makeText(requireContext(), " message sent successfully", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}