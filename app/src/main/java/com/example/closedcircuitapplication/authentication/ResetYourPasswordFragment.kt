package com.example.closedcircuitapplication.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.Validation
import com.example.closedcircuitapplication.databinding.FragmentResetYourPasswordBinding


class ResetYourPasswordFragment : Fragment() {
    private var _binding: FragmentResetYourPasswordBinding? = null
    private val binding get() = _binding!!
    lateinit var  newPassword:String
    lateinit var confirmNewPassword :String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResetYourPasswordBinding.inflate( inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.restYourPasswordEnterNewPassword.addTextChangedListener {
            newPassword =  binding.restYourPasswordEnterNewPassword.text.toString().trim()
            passwordInputValidationListener(newPassword)
        }
        binding.restYourPasswordResetPasswordBtn.setOnClickListener {
            newPassword= binding.restYourPasswordEnterNewPassword.text.toString().trim()
            confirmNewPassword = binding.confirmNewPasswordInput.text.toString().trim()
            navigateToPasswordRecoverySuccessfulFragment(newPassword, confirmNewPassword)

        }

    }

    // this is  used to navigate to PasswordRecoverySuccessfulFragment
    fun navigateToPasswordRecoverySuccessfulFragment(newPassword:String, confirmNewPassword:String){
            if (newPassword.isNotEmpty() && newPassword == confirmNewPassword) {
                findNavController().navigate(R.id.action_resetYourPasswordFragment_to_passwordRecoverySuccessfulFragment)
            }
        binding.confirmNewPasswordInput.error = "password does not match"
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.resetYourPasswordToolbar.apply {
//            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
//            setNavigationOnClickListener {
//                activity?.onBackPressed()
//            }
//        }

    // check the password input if it meet all the requirement
    fun passwordInputValidationListener(password: String) {

        if (!password.contains(Validation.UPPERCASE) || !password.toString().contains(Validation.LOWERCASE)) {
            binding.restYourPasswordEnterNewPassword.error = "* Upper case and lowercase"
        } else if (!password.contains(Validation.DIGITCHARACTER)) {
            binding.restYourPasswordEnterNewPassword.error = "* Numbers"
        } else if (!password.contains(Validation.SPECAILCHARAACTERS)) {
            binding.restYourPasswordEnterNewPassword.error = "* Special characters"
        } else if (password.length <= 7) {
            binding.restYourPasswordEnterNewPassword.error = "*  Minimum of 8 characters"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}