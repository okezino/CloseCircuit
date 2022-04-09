package com.example.closedcircuitapplication.beneficiary.loan.presentation.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.closedcircuitapplication.common.common.utils.customNavAnimation
import com.example.closedcircuitapplication.databinding.FragmentLoanOfferBinding
import com.example.closedcircuitapplication.beneficiary.loan.data.dto.LoanOfferRequest
import com.example.closedcircuitapplication.beneficiary.loan.presentation.ui.adapters.LoanOfferAdapter
import com.example.closedcircuitapplication.beneficiary.loan.utils.LoanOfferOnclickeListener
import com.example.closedcircuitapplication.utils.loanItemList


class LoanOfferFragment : Fragment() , LoanOfferOnclickeListener {
    private var _binding: FragmentLoanOfferBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoanOfferBinding.inflate(layoutInflater, container, false)
        return  binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val itemList : ArrayList<LoanOfferRequest> = arrayListOf()
        itemList.clear()
        itemList.addAll(loanItemList)
        val loanOfferRecyclerView = binding.loanOfferRecyclerView
        loanOfferRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            val  recyclerViewAdapter = LoanOfferAdapter( itemList, this@LoanOfferFragment)
            adapter = recyclerViewAdapter
        }
    }

    override fun onItemClickListener(position: Int) {
        findNavController().navigate(LoanOfferFragmentDirections.actionLoanOfferFragmentToLoanOfferDetailsFragment(), customNavAnimation().build())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}