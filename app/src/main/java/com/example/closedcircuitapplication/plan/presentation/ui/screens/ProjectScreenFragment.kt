package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.data.preferences.Preferences
import com.example.closedcircuitapplication.common.utils.Resource
import com.example.closedcircuitapplication.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentProjectScreenBinding
import com.example.closedcircuitapplication.plan.domain.models.GenerateOtpRequest
import com.example.closedcircuitapplication.plan.viewModel.PlanViewModel
import com.example.closedcircuitapplication.ui.projectScreens.Projects
import com.example.closedcircuitapplication.ui.projectScreens.ProjectsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProjectScreenFragment : Fragment(R.layout.fragment_project_screen) {
    private var _binding: FragmentProjectScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var projectsAdapter: ProjectsAdapter
    private lateinit var projectsRecyclerView: RecyclerView
    @Inject
    lateinit var preferences: Preferences
    private val viewModel: PlanViewModel by viewModels()
    private var userEmail: String = ""

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

        val prefEmail = preferences.getEmail()
        userEmail = prefEmail

        initObservers()
        fetchProjects()

        binding.fragmentProjectScreenLayout.setOnClickListener {
            val email: String = prefEmail
            viewModel.generateOtp(GenerateOtpRequest(email))
            findNavController().navigate(ProjectScreenFragmentDirections
                .actionProjectScreenFragmentToEmailVerificationFragment(), customNavAnimation().build())
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
        projectsAdapter = ProjectsAdapter(projects)

        projectsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        projectsRecyclerView.adapter = projectsAdapter
    }

    private fun initObservers(){
        viewModel.generateOtpResponse.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Otp sent to $userEmail", Toast.LENGTH_LONG).show()
                }
                is Resource.Success -> {
                    findNavController().navigate(ProjectScreenFragmentDirections
                        .actionProjectScreenFragmentToEmailVerificationFragment(), customNavAnimation().build())
                    Toast.makeText(requireContext(), "Otp sent to $userEmail", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    //TODO(Display error message and dismiss progress bar)
                    Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
