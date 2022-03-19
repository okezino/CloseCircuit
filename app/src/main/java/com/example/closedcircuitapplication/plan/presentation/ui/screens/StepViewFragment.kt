package com.example.closedcircuitapplication.plan.presentation.ui.screens

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.closedcircuitapplication.R

class StepViewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step_view, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.step_menu, menu)
    }
}