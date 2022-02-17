package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.plan.presentation.ui.adapters.CreateStepsBudgetsFragmentAdapter
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProjectSummaryFragment : Fragment() {

    private var _binding: FragmentCreatePlanBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPagerAdapter: CreateStepsBudgetsFragmentAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreatePlanBinding.inflate(inflater, container, false)
        viewPager = binding.viewPager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.planLink.setOnClickListener {
            findNavController().navigate(R.id.sendFundsSummaryFragment)
        }

        viewPagerAdapter = CreateStepsBudgetsFragmentAdapter(this)
        viewPager.adapter = viewPagerAdapter
        initPagerAdapter()

        tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Steps"
                1 -> tab.text = "Budgets"
            }
        }.attach()
    }

    private fun initPagerAdapter() {
        viewPagerAdapter = CreateStepsBudgetsFragmentAdapter(this)
        viewPager.apply {
            adapter = viewPagerAdapter
            clipToPadding = false
            clipChildren = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
