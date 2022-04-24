package com.example.closedcircuitapplication.beneficiary.loan.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.LoanOfferItemBinding
import com.example.closedcircuitapplication.beneficiary.loan.data.dto.LoanOfferRequest
import com.example.closedcircuitapplication.beneficiary.loan.utils.LoanOfferOnclickeListener

class LoanOfferAdapter (private val loanOfferItemList : ArrayList<LoanOfferRequest>, val listener: LoanOfferOnclickeListener) :
    RecyclerView.Adapter<LoanOfferAdapter.LoanViewHolder>(){
    inner class LoanViewHolder(view : View): RecyclerView.ViewHolder(view){
        private val binding : LoanOfferItemBinding = LoanOfferItemBinding.bind(view)

        init {
            initOnItemClickListener()
        }
        fun bindView(loanItemList: LoanOfferRequest) {
            binding.businessNameLoanItemTv.text = loanItemList.projectName
            binding.valueNumbersOfSponsorTv.text = loanItemList.sponsorImage.size.toString()
            binding.valueTotalAmountOfferedTv.text = loanItemList.totalAmount
            if (loanItemList.sponsorImage.size >= 5){
                binding.itemCountTv.text ="+ ${loanItemList.sponsorImage.size - 5}"
                binding.itemCountTv.setBackgroundResource(loanItemList.sponsorImage[position])
            }else{
                binding.itemCountTv.visibility = View.GONE
            }
            bindChildRecyclerView(loanItemList)
        }

        private fun bindChildRecyclerView(loanItemList: LoanOfferRequest){
            binding.loanSponsorsImageRecyclerView.apply {
                val decorator = StackedImageItemDecorator()
                addItemDecoration(decorator)
                val recyclerView = StackedRecyclerViewAdapter()
                recyclerView.addSponsorImage(loanItemList.sponsorImage)
                adapter = recyclerView
                val stackedLayoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                stackedLayoutManager.reverseLayout = true
                stackedLayoutManager.stackFromEnd = true
                layoutManager = stackedLayoutManager
            }
        }

        private fun initOnItemClickListener(){
            binding.loanOfferLayout.setOnClickListener {
                listener.onItemClickListener(position)
            }
            itemView.setOnClickListener {
                listener.onItemClickListener(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loan_offer_item, parent, false)
        return LoanViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        val loanItemList = loanOfferItemList[position]
        holder.bindView(loanItemList)
    }

    override fun getItemCount(): Int {
        return loanOfferItemList.size

    }
}