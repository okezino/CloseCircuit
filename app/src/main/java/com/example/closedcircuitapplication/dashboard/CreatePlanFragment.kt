package com.example.closedcircuitapplication.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.closedcircuitapplication.dashboard.adapter.CreatePlanFragmentAdapter
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CreatePlanFragment : Fragment() {

    private var _binding: FragmentCreatePlanBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPagerAdapter: CreatePlanFragmentAdapter
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

        viewPagerAdapter = CreatePlanFragmentAdapter(this)
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
        viewPagerAdapter = CreatePlanFragmentAdapter(this)
        viewPager.apply {
            adapter = viewPagerAdapter
            clipToPadding = false
            clipChildren = false
            val marginPageTransformer = MarginPageTransformer(30)
            val compositionPageTransformer = CompositePageTransformer()
            compositionPageTransformer.addTransformer(marginPageTransformer)
            compositionPageTransformer.addTransformer { page, position ->
                page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
            }
            viewPager.setPageTransformer(compositionPageTransformer)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
