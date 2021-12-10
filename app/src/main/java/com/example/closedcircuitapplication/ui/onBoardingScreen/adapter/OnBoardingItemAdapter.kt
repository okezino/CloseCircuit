package com.example.closedcircuitapplication.ui.onBoardingScreen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.ViewPagerTemplateBinding
import com.example.closedcircuitapplication.ui.onBoardingScreen.adapterModel.AdapterModel

class OnBoardingItemAdapter(): RecyclerView.Adapter<OnBoardingItemAdapter.OnBoardingItemViewHolder>() {

    var onBoardingModel: MutableList<AdapterModel> = mutableListOf()


    inner class OnBoardingItemViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val binding: ViewPagerTemplateBinding = ViewPagerTemplateBinding.bind(view)
        private val image = binding.onBoardImage
        private val title = binding.title
        private val description = binding.description
        val layout = binding.layoutViewPager

        fun bind(onBoardingItem: AdapterModel){
            image.setImageResource(onBoardingItem.onBoardingImage)
            title.text = onBoardingItem.title
            description.text =onBoardingItem.description
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
        holder.bind(onBoardingModel[position])
    }

    override fun getItemCount(): Int {
        return onBoardingModel.size
    }
}