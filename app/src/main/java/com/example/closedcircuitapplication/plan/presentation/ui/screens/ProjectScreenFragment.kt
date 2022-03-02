package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.data.preferences.PreferencesConstants
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.common.utils.makeSnackBar
import com.example.closedcircuitapplication.common.utils.UserNameSplitterUtils
import com.example.closedcircuitapplication.databinding.FragmentProjectScreenBinding
import com.example.closedcircuitapplication.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.plan.presentation.models.Plan
import com.example.closedcircuitapplication.plan.presentation.models.Projects
import com.example.closedcircuitapplication.plan.presentation.ui.viewmodels.PlanViewModel
import com.example.closedcircuitapplication.plan.utils.PlanConstants.LOADING
import com.example.closedcircuitapplication.plan.utils.PlanConstants.OTP_MESSAGE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProjectScreenFragment : Fragment(R.layout.fragment_project_screen), ProjectsAdapter.SetItemClickListener {
    private var _binding: FragmentProjectScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var projectsAdapter: ProjectsAdapter
    private lateinit var projectsRecyclerView: RecyclerView
    private var myPlansList =  mutableListOf<Plan>()
    @Inject
    lateinit var preferences: Preferences
    private val viewModel: PlanViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProjectScreenBinding.inflate(inflater, container, false)
        projectsRecyclerView = binding.projectsRecyclerView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myPlansList = ArrayList<Plan>()
        binding.notStartedAPlanTv.text = getString(R.string.user_name, UserNameSplitterUtils.userFullName(preferences.getUserFirstName()))

        initObservers()
        fetchProjects()
        initObserversMyPlans()

        viewModel.getMyPlans(100, 0, "Bearer ${preferences.getToken()}")

        binding.fragmentProjectScreenLayout.setOnClickListener {

            if (!preferences.getUserVerifiedValue(PreferencesConstants.USER_VERIFIED)) {
                viewModel.generateOtp(GenerateOtpRequest(preferences.getEmail()))
                findNavController().navigate(
                    ProjectScreenFragmentDirections
                        .actionProjectScreenFragmentToEmailVerificationFragment(),
                    customNavAnimation().build()
                )
            } else {
                findNavController().navigate(
                    ProjectScreenFragmentDirections.actionProjectScreenFragmentToCreateAPlanFragment2(),
                    customNavAnimation().build()
                )
            }

        }
    }

    private fun fetchProjects() {
        val projects = ArrayList<Projects>()
        projects.add(
            Projects(
                "School Fees",
                "A plan to raise school fees for the first semester of my first year of Mechanical Engineering",
                "10",
                "60",
                "8",
                "Projects"
            )
        )
        projects.add(
            Projects(
                "Fish Farming",
                "To build a mega catfish farm in a viable area within the city of Lagos, Nigeria",
                "4",
                "10",
                "5",
                "Business"
            )
        )
        projects.add(
            Projects(
                "Book Launch",
                "A passion project to launch a new book on the effects of the COVID-19 on modern workplace culture",
                "5",
                "20",
                "2",
                "Project"
            )
        )
        projectsAdapter = ProjectsAdapter(myPlansList)

        projectsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        projectsRecyclerView.adapter = projectsAdapter
    }

    private fun initObservers() {
        viewModel.generateOtpResponse.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    makeSnackBar(LOADING, requireView())
                }
                is Resource.Success -> {
                    findNavController().navigate(
                        ProjectScreenFragmentDirections
                            .actionProjectScreenFragmentToEmailVerificationFragment(),
                        customNavAnimation().build()
                    )
                    makeSnackBar("$OTP_MESSAGE ${preferences.getEmail()}", requireView())
                }
                is Resource.Error -> {
                    makeSnackBar("${resource.data?.message}", requireView())
                }
            }
        }
    }

    private fun initObserversMyPlans(){
        viewModel.getMyPlansResponse.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    makeSnackBar("Loading...", requireView())
                }
                is Resource.Success -> {
                    resource.data.data?.plans
                    val res = resource.datas?.data!!.plans[0].business_name
                    Log.d("listofplans", res)
                    Log.d("listofplans2", "${resource.datas.data.plans}")

                    projectsAdapter = ProjectsAdapter(resource.data.data!!.plans, )

                    projectsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                    resource.data.data.plans.let {
                        projectsAdapter.submitList(resource.data.data.plans)
                        projectsAdapter.notifyDataSetChanged()
                        projectsRecyclerView.adapter = projectsAdapter
                    }

                    if (resource.data.data?.plans == null){
                        binding.emptyProject.visibility = View.VISIBLE
                    }else{
                        binding.projectsRecyclerView.visibility = View.VISIBLE
                        binding.emptyProject.visibility = View.GONE
                    }
                }
                is Resource.Error -> {
                    makeSnackBar("${resource.data?.message}",requireView())
                }
            }
        }
    }

    override fun allPlansItemClicked(position: Int) {
        makeSnackBar("clicked...", requireView())
    }
}
