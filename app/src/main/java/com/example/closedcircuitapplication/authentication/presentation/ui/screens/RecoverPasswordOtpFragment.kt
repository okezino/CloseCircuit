package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentRecoverPasswordOtpBinding


class RecoverPasswordOtpFragment : Fragment() {
    private var _binding: FragmentRecoverPasswordOtpBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecoverPasswordOtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpannableText()
      validateOtp()
//
//        binding.recoverPasswordOtpToolbar.apply {
//            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
//            setNavigationOnClickListener {
//                activity?.onBackPressed()
//            }
//        }
        //navigate from recover password screen with otp to reset password screen
    }

    private fun setUpSpannableText() {
        val text ="Didn’t recieve email? resend"
        val spannableText = SpannableString(text)
        val foregroundBlue = ForegroundColorSpan(requireActivity().resources.getColor(R.color.spannableBlue))
        spannableText.setSpan(foregroundBlue, 22, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.recoverPasswordOtpDidntReceiveEmailTextView.text = spannableText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
// this function is used to validate the otp
    fun validateOtp(){
        val pin=binding.recoverPasswordOtpView.text
        binding.recoverPasswordOtpView.addTextChangedListener {
        if (pin.toString().length == 4){
            if (pin.toString() == "1234"){
                findNavController().navigate(R.id.action_recoverPasswordOtpFragment_to_resetYourPasswordFragment)

            }else{
                binding.recoverPasswordOtpView.setLineColor( resources.getColor(R.color.red))
                binding.recoverPasswordOtpView.setTextColor(resources.getColor(R.color.red))
            }
    }
    }}
}