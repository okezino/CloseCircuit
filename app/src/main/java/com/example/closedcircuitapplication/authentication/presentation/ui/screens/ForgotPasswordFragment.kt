package com.example.closedcircuitapplication.authentication.presentation.ui.screens

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.authentication.presentation.ui.viewmodels.AuthenticationViewModel
import com.example.closedcircuitapplication.common.presentation.utils.showCustomViewDialog
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.Validation
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentForgotPasswordBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    lateinit var email:String
    private val viewModel: AuthenticationViewModel by viewModels()
    private var userEmail: String = ""
    private var pleaseWaitDialog: AlertDialog? = null

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


        initObservers()

        binding.forgotPasswordEmailTv.addTextChangedListener(buttonHandler)

        binding.forgotPasswordButton.setOnClickListener {
            val email = binding.forgotPasswordEmailTv.text.toString().trim()

            if(Validation.validateEmailPattern(email)){
                viewModel.generateOtp(GenerateOtpRequest(email))
                userEmail = email

                findNavController().navigate(
                    ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToRecoverPasswordOtpFragment(userEmail),
                    customNavAnimation().build()
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
    private fun setUpSpannableText() {
        val text ="Remember password? Login"
        val spannableText = SpannableString(text)
        val foregroundBlue = ForegroundColorSpan(requireActivity().resources.getColor(R.color.spannableBlue))
        spannableText.setSpan(foregroundBlue, 19, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.forgotPasswordRememberPasswordTextView.text = spannableText
    }

    private fun initObservers(){
        viewModel.generateOtpRequest.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    //TODO(Show Progress bar)
                    showPleaseWaitAlertDialog().dismiss()
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    //TODO(Move to Dashboard)
                    showPleaseWaitAlertDialog().dismiss()
                    val action =
                        ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToRecoverPasswordOtpFragment(
                            userEmail
                        )
                    findNavController().navigate(
                        action
                    )
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                }

                is Resource.Error -> {
                    showPleaseWaitAlertDialog().dismiss()
                    //TODO(Display error message and dismiss progress bar)
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT)
                        .show()

                }
            }


        }

    }

    private fun showPleaseWaitAlertDialog(): AlertDialog {
        if(pleaseWaitDialog == null) {
            pleaseWaitDialog = showCustomViewDialog(
                requireContext(), resources,
                R.layout.custom_login_wait_dialog,
                false
            )
        }
       return pleaseWaitDialog as AlertDialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}