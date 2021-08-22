package com.lemillion.minke.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lemillion.minke.adapter.TransactionViewAdapter
import com.lemillion.minke.databinding.FragmentUnenrichedTransactionBinding
import com.lemillion.minke.viewmodels.UnenrichedTransactionListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnenrichedTransactionFragment : Fragment() {

    private var transactionViewAdapter = TransactionViewAdapter()
    private val viewModel: UnenrichedTransactionListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentUnenrichedTransactionBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.rvTransactions.adapter = transactionViewAdapter
        subscribeUi()
        return binding.root
    }

    private fun subscribeUi() {
        viewModel.unenrichedTransactions.observe(
            viewLifecycleOwner,
            { list ->
                transactionViewAdapter.submitList(list)
            })
    }
}