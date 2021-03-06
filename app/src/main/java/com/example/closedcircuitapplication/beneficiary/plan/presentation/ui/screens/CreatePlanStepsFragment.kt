package com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.common.data.network.models.Step
import com.example.closedcircuitapplication.common.common.utils.Resource
import com.example.closedcircuitapplication.beneficiary.dashboard.presentation.view.interfaces.ClickListener
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanStepsBinding
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.adapters.StepsBudgetsAdapter
import com.example.closedcircuitapplication.beneficiary.plan.presentation.ui.viewmodels.AllStepsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePlanStepsFragment : Fragment(), ClickListener {

    private var _binding: FragmentCreatePlanStepsBinding? = null
    private val binding get() = _binding!!

    private lateinit var stepsAdapter: StepsBudgetsAdapter
    private lateinit var stepsRecyclerView: RecyclerView
    private val viewModel: AllStepsViewModel by activityViewModels()
    private var currentStepList: ArrayList<Step> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreatePlanStepsBinding.inflate(inflater, container, false)
        stepsRecyclerView = binding.stepsItemRecyclerView
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getProjectSteps()
        stepsAdapter.submitList(currentStepList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoButton = binding.contentInfo
        val stepsInfo = binding.infoDialogLayout
        val infoTitle = stepsInfo.infoDialogTitle
        val infoDescription = stepsInfo.infoDialogDescription
        val infoCloseButton = stepsInfo.closeDialogIcon

        infoTitle.text = getString(R.string.steps_info_title)
        infoDescription.text = getString(R.string.steps_info_description)

        infoCloseButton.setOnClickListener {
            stepsInfo.infoItemLayout.visibility = View.INVISIBLE
        }

        infoButton.setOnClickListener {
            if (stepsInfo.infoItemLayout.isVisible) {
                stepsInfo.infoItemLayout.visibility = View.INVISIBLE
            } else {
                stepsInfo.infoItemLayout.visibility = View.VISIBLE
            }
        }
        viewModel.getStepsResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    val stepsForPlan = arrayListOf<Step>()
                    for (step in it.datas?.data!!.steps) {
                        if (step.plan == viewModel.planId) {
                            stepsForPlan.add(step)
                        }
                    }
                    currentStepList = stepsForPlan
                    stepsAdapter.submitList(stepsForPlan)
                    stepsAdapter.notifyDataSetChanged()
                }
                else -> {
                   // makeSnackBar(getString(R.string.Unable_to_get_steps), requireView())
                }
            }
        }
        getProjectSteps()
    }


    private fun getProjectSteps() {
        stepsAdapter = StepsBudgetsAdapter(this)

        stepsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        stepsRecyclerView.adapter = stepsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(budgetDate: Step) {
        val navHost =
            requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val controller = navHost.navController
        val bundle = Bundle()
        bundle.putParcelable(getString(R.string.budget_data), budgetDate)
        controller.navigate(R.id.stepViewFragment, bundle)
    }

}
