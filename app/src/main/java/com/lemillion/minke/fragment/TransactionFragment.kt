package com.lemillion.minke.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lemillion.minke.adapter.TransactionViewAdapter
import com.lemillion.minke.databinding.FragmentTransactionBinding
import com.lemillion.minke.viewmodels.TransactionListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment : Fragment() {

    private var transactionViewAdapter = TransactionViewAdapter()
    private val viewModel: TransactionListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTransactionBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.rvTransactions.adapter = transactionViewAdapter
        subscribeUi()
        return binding.root
    }

    private fun subscribeUi() {
        viewModel.transactions.observe(
            viewLifecycleOwner,
            { list ->
                transactionViewAdapter.submitList(list)
            })
    }
}