package com.example.closedcircuitapplication.beneficiary.loan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentLoanOfferDetailsBinding
import com.example.closedcircuitapplication.beneficiary.loan.data.dto.LoanOfferDetailDto
import com.example.closedcircuitapplication.beneficiary.loan.presentation.ui.adapters.LoanOfferDetailsAdapter
import com.example.closedcircuitapplication.beneficiary.loan.utils.OnItemClickListener
import com.example.closedcircuitapplication.utils.loanOfferList


class LoanOfferDetailsFragment : Fragment(), OnItemClickListener {
    private var _binding : FragmentLoanOfferDetailsBinding? = null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoanOfferDetailsBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val loanOfferRecyclerView = binding.loanOfferDetailRv
        val loanItemList :ArrayList<LoanOfferDetailDto> = arrayListOf()
        loanItemList.clear()
        loanItemList.addAll(loanOfferList)
        loanOfferRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val recyclerViewAdapter = LoanOfferDetailsAdapter(requireContext(),loanItemList, this@LoanOfferDetailsFragment)
            adapter = recyclerViewAdapter
        }

    }
    override fun onItemClickListener(position: Int) {
        findNavController().navigate(LoanOfferDetailsFragmentDirections.actionLoanOfferDetailsFragmentToLoanDetailFragment(), customNavAnimation().build())

    }

    override fun onDeclineBtnClicked(position: Int) {
        findNavController().navigate(LoanOfferDetailsFragmentDirections.actionLoanOfferDetailsFragmentToLoanDetailFragment(), customNavAnimation().build())
    }

    override fun onAcceptBtnClicked(position: Int) {
        findNavController().navigate(LoanOfferDetailsFragmentDirections.actionLoanOfferDetailsFragmentToLoanDetailFragment(), customNavAnimation().build())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}