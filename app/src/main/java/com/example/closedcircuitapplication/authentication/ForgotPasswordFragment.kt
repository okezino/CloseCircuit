package com.example.closedcircuitapplication.authentication

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpannableText()
//        binding.forgotPasswordToolbar.apply {
//            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
//            setNavigationOnClickListener {
//                activity?.onBackPressed()
//            }
//        }

        //navigate to password recovery screen
        binding.forgotPasswordButton.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_recoverPasswordOtpFragment)
        }

    }

    private fun setUpSpannableText() {
        val text ="Remember password? Login"
        val spannableText = SpannableString(text)
        val foregroundBlue = ForegroundColorSpan(requireActivity().resources.getColor(R.color.spannableBlue))
        spannableText.setSpan(foregroundBlue, 19, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.forgotPasswordRememberPasswordTextView.text = spannableText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}