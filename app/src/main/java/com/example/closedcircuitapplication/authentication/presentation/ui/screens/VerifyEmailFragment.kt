package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentVerifyEmailBinding

class VerifyEmailFragment : Fragment() {

    private var _binding: FragmentVerifyEmailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentVerifyEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.resendEmailTv.setTextColor(resources.getColor(R.color.blue))
        val pin = binding.pinView.text

        // this is use to check the input value of the pinview if it correct with the OTP sent to the user email
        binding.pinView.addTextChangedListener {
            binding.pinView.setLineColor(resources.getColor(R.color.green_700))
            binding.wrongcodeTv.visibility = View.INVISIBLE
            if (pin.toString().length == 4) {
                if (pin.toString() == "1234") {
                    findNavController().navigate(VerifyEmailFragmentDirections.actionVerifyEmailFragmentToEmailVerificationSuccessfulFragment(),
                        customNavAnimation().build())
                } else {
                    binding.wrongcodeTv.visibility = View.VISIBLE
                    binding.pinView.setLineColor(resources.getColor(R.color.red))
                    binding.wrongcodeTv.setTextColor(resources.getColor(R.color.red))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
