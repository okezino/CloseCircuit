package com.example.closedcircuitapplication.authentication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.Validation
import com.example.closedcircuitapplication.databinding.FragmentForgotPasswordBinding
import com.google.android.material.snackbar.Snackbar

class ForgotPasswordFragment : Fragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    lateinit var email:String

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
//        setUpSpannableText()
////        binding.forgotPasswordToolbar.apply {
////            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
////            setNavigationOnClickListener {
////                activity?.onBackPressed()
////            }
////        }
//
//        //navigate to password recovery screen
//        binding.forgotPasswordButton.setOnClickListener {
//            email = binding.forgotPasswordEmailTv.text.toString().trim()
//            emailTextInputValidation(email)
//        }
//        binding.forgotPasswordEmailTv.addTextChangedListener {
//            email = binding.forgotPasswordEmailTv.text.toString().trim()
//            onEmailTextInputChangeListener(email)
//        }

        binding.forgotPasswordEmailTv.addTextChangedListener(buttonHandler)

        binding.forgotPasswordButton.setOnClickListener {
            val email = binding.forgotPasswordEmailTv.text.toString().trim()

            if(Validation.validateEmailPattern(email)){
                findNavController().navigate(
                    R.id.action_forgotPasswordFragment_to_recoverPasswordOtpFragment
                )
            }else{
                Snackbar.make(
                    binding.root,
                    "Invalid email address",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

    }

    private val buttonHandler: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val email = binding.forgotPasswordEmailTv.text.toString().trim()
            binding.forgotPasswordButton.isEnabled =
                email.isNotEmpty()
        }
        override fun afterTextChanged(p0: Editable?) {}

    }
//
//    private fun setUpSpannableText() {
//        val text ="Remember password? Login"
//        val spannableText = SpannableString(text)
//        val foregroundBlue = ForegroundColorSpan(requireActivity().resources.getColor(R.color.spannableBlue))
//        spannableText.setSpan(foregroundBlue, 19, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        binding.forgotPasswordRememberPasswordTextView.text = spannableText
//    }
//    fun emailTextInputValidation(email:String){
//        if (Validation.validateEmailInput(email)){
//            findNavController().navigate(R.id.action_forgotPasswordFragment_to_recoverPasswordOtpFragment)
//        }
//    }
//    fun onEmailTextInputChangeListener(email:String){
//        if (!Validation.validateEmailInput(email)) {
//            binding.forgotPasswordEmailTv.error = "wrong email address"
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}