package com.example.closedcircuitapplication.beneficiary.dashboard.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.beneficiary.dashboard.presentation.models.DonationItem
import com.example.closedcircuitapplication.databinding.RecentDonationsItemBinding

class RecentDonationsAdapter(private val donations: MutableList<DonationItem>) : RecyclerView.Adapter<RecentDonationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecentDonationsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = donations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(donations[position])
    }

    class ViewHolder(binding: RecentDonationsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val sponsor: TextView = binding.donorNameTextView
        private val amountDonated: TextView = binding.amountDonatedTextView
        private val planName: TextView = binding.planNameTextView
        private val sponsorImage: ImageView = binding.sponsorImageView

        fun bindView(donation: DonationItem) {
            sponsor.text = donation.sponsorName
            amountDonated.text = donation.amount
            planName.text = donation.planName
            sponsorImage.setImageDrawable(
                ResourcesCompat.getDrawable(
                    sponsorImage.resources,
                    donation.sponsorImage, null
                )
            )
        }
    }
}
