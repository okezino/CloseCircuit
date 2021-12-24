package com.example.closedcircuitapplication.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentCreatePlanStepsBinding

class CreatePlanStepsFragment : Fragment() {

    private var _binding: FragmentCreatePlanStepsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreatePlanStepsBinding.inflate(inflater, container, false)
        return binding.root
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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
