package com.example.closedcircuitapplication.beneficiary.loan.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.closedcircuitapplication.R
import com.example.closedcircuitapplication.databinding.LoanOfferDetailItemBinding
import com.example.closedcircuitapplication.beneficiary.loan.data.dto.LoanOfferDetailDto
import com.example.closedcircuitapplication.beneficiary.loan.utils.OnItemClickListener

class LoanOfferDetailsAdapter (val context : Context, private val detailList:ArrayList<LoanOfferDetailDto>, val listener : OnItemClickListener) : RecyclerView.Adapter<LoanOfferDetailsAdapter.DetailViewHolder>() {
    inner class DetailViewHolder(itemView : View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val binding: LoanOfferDetailItemBinding = LoanOfferDetailItemBinding.bind(itemView)
        val loanOfferDetailLayout: View = binding.loanOfferDetailLayout

        init {
            itemView.setOnClickListener(this)
        }

        fun bindView(items : LoanOfferDetailDto){
            binding.loanSponsorsNameTv.text = items.sponsorsName
            binding.loanAmountOfferedTv.text = context.getString(R.string.amount, "NGN", items.amount)
            binding.gracePeriodTv.text = context.getString(R.string.grace_period_months, items.gracePeriod)
            binding.loanPercentageTv.text = context.getString(R.string.loan_percentage, items.loanPercentage)
            binding.loanSponsorProfileIV.setImageResource(items.sponsorImage)

            acceptLoanOfferClickListener()
            declineLoanOfferClickLister()
        }
        override fun onClick(p0: View?) {
            listener.onItemClickListener(position)
        }

        private fun declineLoanOfferClickLister(){
            val declineLoanOfferBtn: Button = binding.declineLoanOfferDetailBtn
            declineLoanOfferBtn.setOnClickListener {
                listener.onDeclineBtnClicked(position)
            }
        }
        private fun acceptLoanOfferClickListener(){
            val acceptLoanOfferBtn: Button = binding.acceptLoanOfferDetailBtn
            acceptLoanOfferBtn.setOnClickListener {
                listener.onAcceptBtnClicked(position)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loan_offer_detail_item, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val detailItems = detailList[position]
        holder.bindView(detailItems)
        holder.loanOfferDetailLayout.setOnClickListener { listener.onItemClickListener(position) }
    }

    override fun getItemCount() = detailList.size
}