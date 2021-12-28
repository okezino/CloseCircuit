package com.example.closedcircuitapplication.ui.projectScreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentProjectScreenBinding

class ProjectScreenFragment : Fragment(R.layout.fragment_project_screen) {
    private var _binding: FragmentProjectScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProjectScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView4.setOnClickListener {
            findNavController().navigate(R.id.emailVerificationFragment)
        }
        binding.imageView3.setOnClickListener {
            findNavController().navigate(R.id.emailVerificationFragment)
        }
    }
}
