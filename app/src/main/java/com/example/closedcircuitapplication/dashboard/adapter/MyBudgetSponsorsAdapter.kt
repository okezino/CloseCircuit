package com.example.closedcircuitapplication.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.dashboard.models.SponsorsItem
import com.example.closedcircuitapplication.databinding.MyBudgetSponsorItemLayoutBinding

class MyBudgetSponsorsAdapter :
    RecyclerView.Adapter<MyBudgetSponsorsAdapter.MyBudgetSponsorViewHolder>() {

    private var sponsorsList = arrayListOf<SponsorsItem>()

    inner class MyBudgetSponsorViewHolder(var binding: MyBudgetSponsorItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sponsor: SponsorsItem) {
            binding.myBudgetSponsorNameTextView.text = sponsor.sponsorName
            binding.myBudgetSponsorLoanOrDonationTextView.text = sponsor.sponsorshipType
            binding.myBudgetSponsorAmountValueTextView.text = sponsor.sponsorshipAmount
            if (sponsor.eligibleApprover) binding.myBudgetSponsorEligibleApproverTextView.visibility =
                View.VISIBLE else binding.myBudgetSponsorEligibleApproverTextView.visibility =
                View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBudgetSponsorViewHolder {
        val inflater = MyBudgetSponsorItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyBudgetSponsorViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyBudgetSponsorViewHolder, position: Int) {
        holder.bind(sponsorsList[position])
    }

    override fun getItemCount(): Int = sponsorsList.size

    fun submitList(list: ArrayList<SponsorsItem>) {
        this.sponsorsList = list
    }
}