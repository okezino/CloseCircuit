package com.example.closedcircuitapplication.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.dashboard.adapter.StepsBudgetsAdapter
import com.example.closedcircuitapplication.dashboard.models.StepsBudgetItem
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanStepsBinding

class CreatePlanStepsFragment : Fragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoButton = binding.contentInfo
        val stepsInfo = binding.infoDialogLayout
        val infoTitle = stepsInfo.infoDialogTitle
        val infoDescription = stepsInfo.infoDialogDescription
        val infoCloseButton = stepsInfo.closeDialogIcon
        val createStepsButton = binding.addItemLayout
        val noStepsTextView = binding.noStepsYet

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
            findNavController().navigate(R.id.createStepsFragment)
        }

        getProjectSteps()
    }

    private fun getProjectSteps() {
        val projectSteps = ArrayList<StepsBudgetItem>()
        projectSteps.add(StepsBudgetItem("Design a website", "NGN 0.00", "NGN 0.00"))
        projectSteps.add(StepsBudgetItem("Marketing the product", "NGN 0.00", "NGN 0.00"))
        projectSteps.add(StepsBudgetItem("Maintaining the app", "NGN 0.00", "NGN 0.00"))

        if (projectSteps.isEmpty()) {

        }

        stepsAdapter = StepsBudgetsAdapter(projectSteps)

        stepsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        stepsRecyclerView.adapter = stepsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
