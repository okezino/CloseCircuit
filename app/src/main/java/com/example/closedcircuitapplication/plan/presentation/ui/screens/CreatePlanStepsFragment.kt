package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.dashboard.interfaces.ClickListener
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.plan.presentation.ui.adapters.StepsBudgetsAdapter
import com.example.closedcircuitapplication.plan.presentation.models.StepsBudgetItem
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanStepsBinding

class CreatePlanStepsFragment : Fragment(), ClickListener {

    private var _binding: FragmentCreatePlanStepsBinding? = null
    private val binding get() = _binding!!

    private lateinit var stepsAdapter: StepsBudgetsAdapter
    private lateinit var stepsRecyclerView: RecyclerView

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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoButton = binding.contentInfo
        val stepsInfo = binding.infoDialogLayout
        val infoTitle = stepsInfo.infoDialogTitle
        val infoDescription = stepsInfo.infoDialogDescription
        val infoCloseButton = stepsInfo.closeDialogIcon
        val createStepsButton = binding.addItemLayout

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

        createStepsButton.setOnClickListener {
            findNavController().navigate(R.id.createStepsFragment, null, customNavAnimation().build())
        }

        getProjectSteps()
    }

    private fun getProjectSteps() {
        val projectSteps = ArrayList<StepsBudgetItem>()
        projectSteps.add(StepsBudgetItem("Design a website", "NGN 500000.00", "NGN 0.00"))
        projectSteps.add(StepsBudgetItem("Marketing the product", "NGN 200000.00", "NGN 200000.00"))

        val noStepsTextView = binding.noStepsYet
        if (projectSteps.isEmpty()) {
            noStepsTextView.visibility = View.VISIBLE
            binding.stepsItemRecyclerView.visibility = View.GONE
        } else {
            binding.stepsItemRecyclerView.visibility = View.VISIBLE
            noStepsTextView.visibility = View.GONE
        }

        stepsAdapter = StepsBudgetsAdapter(projectSteps, this)

        stepsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        stepsRecyclerView.adapter = stepsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(budgetDate: StepsBudgetItem) {
        val navHost = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        var controller = navHost.navController
        val bundle = Bundle()
        bundle.putParcelable("budgetData", budgetDate)
        controller.navigate(R.id.myStepFragment, bundle)
    }
}
