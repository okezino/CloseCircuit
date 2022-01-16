package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.dashboard.BeneficiaryDashboardActivity
import com.example.closedcircuitapplication.databinding.FragmentEmailVerificationSuccessfulBinding

class EmailVerificationSuccessfulFragment : Fragment() {

    private var _binding: FragmentEmailVerificationSuccessfulBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEmailVerificationSuccessfulBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.proceedBtn.setOnClickListener {
//            val intentBeneficiaryDashboard = Intent(requireContext(), BeneficiaryDashboardActivity::class.java)
//            startActivity(intentBeneficiaryDashboard)

            findNavController().navigate(R.id.action_successfulEmailVerificationScreenFragment_to_createAPlanFragment22)
        }
    }
}
