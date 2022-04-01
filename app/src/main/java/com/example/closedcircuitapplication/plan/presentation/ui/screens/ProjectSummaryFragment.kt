package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.utils.deletePlanAlertDialog
import com.example.closedcircuitapplication.common.utils.makeSnackBar
import com.example.closedcircuitapplication.databinding.DeletePlanDialogBinding
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanBinding
import com.example.closedcircuitapplication.plan.presentation.ui.adapters.CreateStepsBudgetsFragmentAdapter
import com.example.closedcircuitapplication.plan.presentation.ui.viewmodels.AllStepsViewModel
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
    private val allStepsViewModel: AllStepsViewModel by activityViewModels()
    private lateinit var planName: TextView
    private lateinit var planDuration: TextView
    private lateinit var planImage: ImageView
    private lateinit var sector: TextView
    private val args: ProjectSummaryFragmentArgs by navArgs()
    private lateinit var planId: String
    private lateinit var _planName: String
    private lateinit var _planSector: String
    private lateinit var _planDuration: String
    private lateinit var _planDescription: String
    private lateinit var _planCategory: String
    private lateinit var _planSellingPrice: String
    private lateinit var _planCostPrice: String

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

        initObservers()
        planName = binding.planName

        planId = args.planId
        allStepsViewModel.planId = planId
        binding.projectSummaryAddStepsButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(getString(R.string.plan_id), planId)
            findNavController().navigate(R.id.createStepsFragment,bundle, customNavAnimation().build())
        }
        allStepsViewModel.getUserSteps("Bearer ${preferences.getToken()}")
        allStepsViewModel.getUserBudgets("Bearer ${preferences.getToken()}")

       val deletePlanDialogBinding = DeletePlanDialogBinding.inflate(layoutInflater)

        planDuration = binding.projectDuration
        planImage = binding.planImageView
        sector = binding.projectBusinessSector

        viewModel.getPlan(planId, "Bearer ${preferences.getToken()}")
        setUpObserver()

        binding.projectSummaryMoreOptions.setOnClickListener {
            val popMenu = PopupMenu(requireContext(), it)
            popMenu.setOnMenuItemClickListener {
                when (it.itemId){
                    R.id.edit_plan -> {
                        findNavController().navigate(ProjectSummaryFragmentDirections.actionCreatePlanFragmentToEditPlanFragment(
                            planId,
                            _planName,
                            _planSector,
                            _planDuration,
                            _planDescription,
                            _planCategory,
                            _planSellingPrice,
                            _planCostPrice
                        ),customNavAnimation().build() )
                        true
                    }
                    R.id.delete_plan -> {
                        planName = binding.planName
                        deletePlanAlertDialog(deletePlanDialogBinding, planName) { deletePlan() }.show()
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

    private fun deletePlan(){
        viewModel.deletePlan(planId, "Bearer ${preferences.getToken()}")
    }
    private fun initObservers() {
        viewModel.deletePlanResponse.observe(viewLifecycleOwner){ resources ->
            when(resources){
               is Resource.Loading ->{
                Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
               }
                is Resource.Success ->{
                    findNavController().navigate(ProjectSummaryFragmentDirections.actionCreatePlanFragmentToProjectScreenFragment(), customNavAnimation().build())
                    Toast.makeText(requireContext(),
                        getString(R.string.plan_deleted) + resources.data!!.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Error ->{
                    Toast.makeText(requireContext(), "${resources.data!!.errors}", Toast.LENGTH_SHORT).show()
                }
            }
        }
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
                    Glide.with(this)
                        .load(Uri.parse(it.data.data?.avatar))
                        .apply(RequestOptions.circleCropTransform())
                        .into(binding.planImageView)
                    sector.text = it.data.data?.plan_sector
                    _planDuration = it.data.data?.plan_duration.toString()
                    _planName = it.data.data?.business_name.toString()
                    _planSector = it.data.data?.plan_sector.toString()
                    _planDescription = it.data.data?.plan_description.toString()
                    _planCategory = it.data.data?.plan_category.toString()
                    _planCostPrice = it.data.data?.estimated_cost_price.toString()
                    _planSellingPrice = it.data.data?.estimated_selling_price.toString()
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
