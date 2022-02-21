package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.compose.ui.text.resolveDefaults
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.presentation.utils.showCustomViewDialog
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.plan.presentation.ui.adapters.MyStepsBudgetAdapter
import com.example.closedcircuitapplication.plan.presentation.models.StepsBudgetItem
import com.example.closedcircuitapplication.databinding.FragmentMyStepBinding
import com.example.closedcircuitapplication.plan.domain.models.DeletePlanRequest
import com.example.closedcircuitapplication.plan.viewModel.PlanViewModel
import com.example.closedcircuitapplication.utils.budgetList
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text
import javax.inject.Inject

@AndroidEntryPoint
class MyStepFragment : Fragment() {
    private var _binding: FragmentMyStepBinding? = null
    private val binding get() = _binding!!
    val budgetAdapter = MyStepsBudgetAdapter()
    private lateinit var budgetData: StepsBudgetItem
    private lateinit var yesButton : TextView
    private lateinit var  noButton : TextView
    val planName = "Marvin LTD"
    private lateinit var  dialogMessage : TextView
    private  var   deleteDialog : Dialog? = null
    private val viewModel: PlanViewModel by viewModels<PlanViewModel>()

    @Inject
    lateinit var preferences : Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyStepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        budgetData = arguments?.getParcelable<StepsBudgetItem>("budgetData")!!

        initObservers()

        val dialogBinding = layoutInflater.inflate(R.layout.delete_plan_dialog, null)
        yesButton = dialogBinding.findViewById(R.id.delete_plan_yes_btn)
        noButton = dialogBinding.findViewById(R.id.delete_plan_No_btn)
         dialogMessage = dialogBinding.findViewById(R.id.delete_dialog_tv)
        dialogMessage.setText(getString(R.string.deletePlan_dialog_message, planName))
        deleteDialog = Dialog(requireContext())
        deleteDialog!!.setContentView(dialogBinding)
        deleteDialog!!.setCancelable(true)
        deleteDialog!!.window?.setBackgroundDrawable(resources.getDrawable(R.drawable.dailog_bg_color))

        val planId  = "12ec1984-bb33-4773-a6b4-e0fbcea71daf"
        val toke = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjQ1NTQwMzMyLCJqdGkiOiI2OTBhNDk1NTc3YjA0MzY3YmM5YTVmMDk0MDJkN2QxZSIsInVzZXJfaWQiOiI1ZjE5MjQwZi1iM2Y2LTQyMTYtYTIwNC1iODBjOGNkZTFlYjQifQ.ZTu-vXOLtU-P00Wxc0j47YDrNU1glja_xEynk2SPppA"
        val token = preferences.getToken()

        budgetAdapter.submitList(budgetList)
        manageUiView()
        binding.myStepsRecyclerView.apply {
            adapter = budgetAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }


        binding.myStepDeleteIconImageView.setOnClickListener {
                deleteDialog!!.show()
        }

        // delete the plan when the yes button is clicked on the dialog
        yesButton.setOnClickListener {
            Log.d("token" ,"$token")
            viewModel.deletePlan(planId, "Bearer $toke")
            deleteDialog!!.dismiss()
        }
        noButton.setOnClickListener {
            Toast.makeText(requireContext(), " action declined", Toast.LENGTH_SHORT).show()
            deleteDialog!!.dismiss()
        }
    }



    private fun manageUiView() {
        if (budgetData != null) {
            binding.myStepTargetAmountValueTextView.text = budgetData.targetFunds
            binding.myStepsFundsRaisedValueTextView.text = budgetData.totalFundsRaised
        }

       if (binding.myStepTargetAmountValueTextView.text == binding.myStepsFundsRaisedValueTextView.text) {
           binding.myStepStepFundedCheckMarkImageView.visibility = View.VISIBLE
           binding.myStepDeleteIconImageView.setImageResource(R.drawable.ic_blurred_trash)
           binding.myStepsEditIconImageView.setImageResource(R.drawable.ic_blurred_edit)
       }
    }

    private fun initObservers(){
        viewModel.deletePlanResponse.observe(viewLifecycleOwner){ resources ->
            when (resources){
                is Resource.Loading ->{
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success ->{
                    Toast.makeText(requireContext(), "Plan has been deleted", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error ->{
                    Snackbar.make( binding.root, resources.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}