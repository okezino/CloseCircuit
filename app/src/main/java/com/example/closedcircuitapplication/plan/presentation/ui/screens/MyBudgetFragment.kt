package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedcircuitapplication.plan.presentation.ui.adapters.MyBudgetSponsorsAdapter
import com.example.closedcircuitapplication.databinding.FragmentMyBudgetBinding
import com.example.closedcircuitapplication.utils.sponsorsList

class MyBudgetFragment : Fragment() {
    private var _binding: FragmentMyBudgetBinding? = null
    private val binding get() = _binding!!
    private val sponsorsAdapter = MyBudgetSponsorsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sponsorsAdapter.submitList(sponsorsList)
        binding.myBudgetRecyclerView.apply {
            adapter = sponsorsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}