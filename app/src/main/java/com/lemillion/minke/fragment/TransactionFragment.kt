package com.lemillion.minke.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.composethemeadapter.MdcTheme
import com.lemillion.minke.databinding.FragmentTransactionBinding
import com.lemillion.minke.view.TransactionsView
import com.lemillion.minke.viewmodel.TransactionListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionFragment : Fragment() {

    private val viewModel: TransactionListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTransactionBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.viewTransactions.setContent {
            MdcTheme {
                TransactionsView(viewModel)
            }
        }
        return binding.root
    }
}