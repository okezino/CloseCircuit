package com.example.closedcircuitapplication.common.authentication.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.common.authentication.presentation.models.AdapterModel
import com.example.closedcircuitapplication.databinding.ViewPagerTemplateBinding

class OnBoardingItemAdapter(private val onBoardingItem: List<AdapterModel>): RecyclerView.Adapter<OnBoardingItemAdapter.OnBoardingItemViewHolder>() {

    inner class OnBoardingItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val binding: ViewPagerTemplateBinding = ViewPagerTemplateBinding.bind(view)
        private val image = binding.onBoardImage
        private val title = binding.title
        private val description = binding.title2
        val layout = binding.layoutViewPager

        fun bind(onBoardingItem: AdapterModel){
            image.setImageResource(onBoardingItem.onBoardingImage)
            title.text = onBoardingItem.title
            description.text = onBoardingItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingItemViewHolder {
        return OnBoardingItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_pager_template,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingItemViewHolder, position: Int) {
        holder.bind(onBoardingItem[position])
    }

    override fun getItemCount(): Int {
        return onBoardingItem.size
    }
}