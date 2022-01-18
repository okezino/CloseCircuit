package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentProjectScreenBinding
import com.example.closedcircuitapplication.ui.projectScreens.Projects
import com.example.closedcircuitapplication.plan.presentation.ui.adapters.ProjectsAdapter

class ProjectScreenFragment : Fragment(R.layout.fragment_project_screen) {
    private var _binding: FragmentProjectScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var projectsAdapter: ProjectsAdapter
    private lateinit var projectsRecyclerView: RecyclerView

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
        fetchProjects()
        binding.textView4.setOnClickListener {
            findNavController().navigate(R.id.emailVerificationFragment)
        }
        binding.imageView3.setOnClickListener {
            findNavController().navigate(R.id.emailVerificationFragment)
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
}
