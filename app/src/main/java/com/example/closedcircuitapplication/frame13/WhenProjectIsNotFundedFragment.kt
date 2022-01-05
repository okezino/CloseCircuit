package com.example.closedcircuitapplication.frame13

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentWhenProjectIsNotFundedBinding


class WhenProjectIsNotFundedFragment : Fragment(R.layout.fragment_when_project_is_not_funded) {
    private var _binding : FragmentWhenProjectIsNotFundedBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWhenProjectIsNotFundedBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

}