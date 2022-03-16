package com.example.closedcircuitapplication.settings.presentation.ui.screens


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentSettingsBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentSettingsBackArrowIv.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.fragmentSettingsThermometerChevron.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToSettingsResetPasswordFragment(), customNavAnimation().build())
        }
        binding.fragmentSettingsChangeCurrencyChevron.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToSelectCurrencyTypeFragment(), customNavAnimation().build())
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}