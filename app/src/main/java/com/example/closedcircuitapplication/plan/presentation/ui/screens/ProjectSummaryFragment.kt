package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.utils.makeSnackBar
import com.example.closedcircuitapplication.plan.presentation.ui.adapters.CreateStepsBudgetsFragmentAdapter
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanBinding
import com.example.closedcircuitapplication.plan.presentation.ui.viewmodels.PlanViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProjectSummaryFragment : Fragment() {

    @Inject
    lateinit var preferences: Preferences
    private var _binding: FragmentCreatePlanBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPagerAdapter: CreateStepsBudgetsFragmentAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val viewModel: PlanViewModel by viewModels()
    private lateinit var planName: TextView
    private lateinit var planDuration: TextView
    private lateinit var planSector: TextView
    private lateinit var planImage: ImageView
    private lateinit var sector: TextView
    private val args: ProjectSummaryFragmentArgs by navArgs()
    private lateinit var planId: String


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

        planName = binding.planName
        planDuration = binding.projectDuration
        planId = args.planId
        planImage = binding.planImageView
        sector = binding.projectBusinessSector

        viewModel.getPlan(planId, "Bearer ${preferences.getToken()}")

        setUpObserver()

        binding.projectSummaryMoreOptions.setOnClickListener {
            val popMenu = PopupMenu(requireContext(), it)
            popMenu.setOnMenuItemClickListener {
                when (it.itemId){
                    R.id.edit_plan -> {
                        Toast.makeText(requireContext(), "Showing Toast", Toast.LENGTH_LONG).show()
                        true
                    }
                    R.id.delete_plan -> {
                        Toast.makeText(requireContext(), "Showing Delete", Toast.LENGTH_LONG).show()
                        true
                    }
                    else -> false
                }
            }
            popMenu.inflate(R.menu.plan_menu)

            try {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            }catch (e: Exception){
            }finally {
                popMenu.show()
            }
        }


//        binding.planLink.setOnClickListener {
//            findNavController().navigate(R.id.sendFundsSummaryFragment)
//        }

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

    private fun setUpObserver() {
        viewModel.getPlanResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                }

                is Resource.Success -> {
                    planName.text =  it.data.data?.business_name
                    planDuration.text = it.data.data?.plan_duration
//                    planImage.setImageResource(it.data.data?.avatar!!.toInt())
                    sector.text = it.data.data?.plan_sector
                    Log.e("testing", "${it.data.data?.plan_duration}")
//                    findNavController().navigate(
//                        CreatePlanStep2FragmentDirections.actionCreatePlanStep2FragmentToPlanCreatedSuccessfullyFragment(),
//                        customNavAnimation().build()
//                    )
                }

                is Resource.Error -> {
                    makeSnackBar("${it.data?.message}", requireView())
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
