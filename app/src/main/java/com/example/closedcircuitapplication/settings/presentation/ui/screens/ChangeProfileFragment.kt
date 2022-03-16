package com.example.closedcircuitapplication.settings.presentation.ui.screens


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentChangeProfileBinding


class ChangeProfileFragment : Fragment() {
    private var _binding: FragmentChangeProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectProfileAdapter()
    }

    private fun selectProfileAdapter(){
        val selectProfile = resources.getStringArray(R.array.select_profile)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, selectProfile)
        with(binding){
            fragmentSettingsChangeProfileDropdown.setAdapter(arrayAdapter)
            fragmentSettingsChangeProfileDropdown.setDropDownBackgroundDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.text_input_background_dropdown,
                    null
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}