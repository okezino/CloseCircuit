package com.example.closedcircuitapplication.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var plansAdapter: PlansAdapter
    private lateinit var plansRecyclerView: RecyclerView
    private lateinit var recentDonationsAdapter: RecentDonationsAdapter
    private lateinit var recentDonationsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        plansRecyclerView = binding.plansConstraint
        recentDonationsRecyclerView = binding.recentDonationsConstraint
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*TODO: Implement Create plan. For now this button displays an active user UI*/
        binding.createPlanButton.setOnClickListener {
            binding.newUserConstraint.visibility = View.GONE
            if (!binding.activeUserConstraint.isVisible) {
                binding.activeUserConstraint.visibility = View.VISIBLE
            }
        }
        createPlans()
        getRecentDonations()
    }

    fun createPlans() {
        val plans = ArrayList<PlanItems>()
        plans.add(PlanItems("School Fees", "20% Funds Raised", "10% Tasks Completed", R.drawable.fish_farming))
        plans.add(PlanItems("Fish Farming", "70% Funds Raised", "40% Tasks Completed", R.drawable.diane_russell))
        plans.add(PlanItems("Book Launch", "52% Funds Raised", "5% Tasks Completed", R.drawable.fish_farming))
        plans.add(PlanItems("Detty December", "20% Funds Raised", "15% Tasks Completed", R.drawable.diane_russell))
        plans.add(PlanItems("Amazon Trip", "5% Funds Raised", "0% Tasks Completed", R.drawable.fish_farming))

        plansAdapter = PlansAdapter(plans)

        plansRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        plansRecyclerView.adapter = plansAdapter
    }

    fun getRecentDonations() {
        val donations = ArrayList<DonationItem>()
        donations.add(DonationItem("Dianne Russell", "NGN 40,000.00", "Fish Farming", R.drawable.diane_russell))
        donations.add(DonationItem("Ayo Makun", "NGN 8,000.00", "Detty December", R.drawable.diane_russell))
        donations.add(DonationItem("Sarah Knowles", "NGN 5,000.00", "Fish Farming", R.drawable.diane_russell))
        donations.add(DonationItem("Jenny Wilson", "NGN 10,000.00", "Fish Farming", R.drawable.diane_russell))
        donations.add(DonationItem("Ronald Richard", "NGN 20,000.00", "School Fees", R.drawable.diane_russell))

        recentDonationsAdapter = RecentDonationsAdapter(donations)

        recentDonationsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recentDonationsRecyclerView.adapter = recentDonationsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
