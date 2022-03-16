package com.example.closedcircuitapplication.settings.presentation.ui.screens

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.utils.*
import com.example.closedcircuitapplication.databinding.FragmentSettingsResetPasswordBinding
import com.example.closedcircuitapplication.plan.utils.PlanConstants.BEARER
import com.example.closedcircuitapplication.settings.domain.models.ChangePasswordRequest
import com.example.closedcircuitapplication.settings.presentation.ui.viewmodel.SettingsViewModel
import com.example.closedcircuitapplication.settings.utils.SettingsConstants
import com.example.closedcircuitapplication.settings.utils.SettingsConstants.ERROR_MESSAGE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SettingsResetPasswordFragment : Fragment() {
    private var _binding: FragmentSettingsResetPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingsViewModel by viewModels()
    @Inject
    lateinit var preferences: Preferences
    private var pleaseWaitDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentSettingsResetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingsResetPasswordInitObserver()
        binding.fragmentSettingsBackArrowIv.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.fragmentSettingsResetPasswordBtn.setOnClickListener {
            settingsResetPassword()
        }
    }

    private fun settingsResetPasswordInitObserver(){
        viewModel.changePasswordResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    pleaseWaitDialog = showPleaseWaitAlertDialog()
                }
                is Resource.Success -> {
                    pleaseWaitDialog?.dismiss()
                    makeSnackBar(SettingsConstants.PASSWORD_UPDATE_SUCCESS, requireView())
                    findNavController().navigate(
                        SettingsResetPasswordFragmentDirections.actionSettingsResetPasswordFragmentToSettingsFragment()
                        ,customNavAnimation().build()
                    )
                }
                is Resource.Error ->{
                    pleaseWaitDialog?.dismiss()
                    makeSnackBar(ERROR_MESSAGE, requireView())
                }
            }
        }
    }

    private fun settingsResetPassword(){
        with(binding){
            val oldPassword = fragmentSettingsResetPasswordEnterOldPasswordEt.text.toString().trim()
            val password = fragmentSettingsResetPasswordEnterNewPasswordEt.text.toString().trim()
            val confirmPassword = fragmentSettingsReEnterNewPasswordEt.text.toString().trim()
            if (Validation.validatePasswordPattern(password)){
                fragmentSettingsChangePasswordErrorTv.visibility = View.GONE
                fragmentSettingsResetPasswordEnterNewPasswordLayout.error = ""
                if (Validation.validatePasswordPattern(confirmPassword)){
                    fragmentSettingsConfirmPasswordErrorTv.visibility = View.GONE
                    fragmentSettingsReEnterNewPasswordLayout.error = ""
                    if (password == confirmPassword){
                        viewModel.changePassword(ChangePasswordRequest(confirmPassword,oldPassword,password),preferences.getUserId(),"$BEARER ${preferences.getToken()}")
                    }else{
                        makeSnackBar(SettingsConstants.PASSWORD_MATCH, requireView())
                    }
                }else{
                    fragmentSettingsReEnterNewPasswordLayout.error = ContextCompat.getColor(requireContext(),R.color.red).toString()
                    fragmentSettingsResetPasswordEnterNewPasswordLayout.errorIconDrawable = null
                    fragmentSettingsConfirmPasswordErrorTv.visibility = View.VISIBLE
                }
            }else{
                fragmentSettingsResetPasswordEnterNewPasswordLayout.error = ContextCompat.getColor(requireContext(),R.color.red).toString()
                fragmentSettingsResetPasswordEnterNewPasswordLayout.errorIconDrawable = null
                fragmentSettingsChangePasswordErrorTv.visibility = View.VISIBLE
            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}