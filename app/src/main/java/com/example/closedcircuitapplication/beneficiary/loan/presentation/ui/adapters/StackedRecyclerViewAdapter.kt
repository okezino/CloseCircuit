package com.example.closedcircuitapplication.beneficiary.loan.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.LoanSponsorsImageItemBinding

class StackedRecyclerViewAdapter: RecyclerView.Adapter<StackedRecyclerViewAdapter.StackedViewHolder>() {
    private val imageList : ArrayList<Int> = arrayListOf()
    inner class StackedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val  binding : LoanSponsorsImageItemBinding = LoanSponsorsImageItemBinding.bind(itemView)

        fun bindView(image : Int){
            binding.loanSponsorIV.setImageResource(image)
        }
    }

    fun addSponsorImage(image : ArrayList<Int>){
        imageList.clear()
        imageList.addAll(image)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackedViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.loan_sponsors_image_item, parent, false)
        return StackedViewHolder(view)
    }

    override fun onBindViewHolder(holder: StackedViewHolder, position: Int) {
        holder.bindView(imageList[position])
    }

    override fun getItemCount(): Int {
        if (imageList.size < 5){
            return  imageList.size
        }
        return 5
    }
}