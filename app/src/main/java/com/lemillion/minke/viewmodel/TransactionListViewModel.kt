package com.lemillion.minke.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lemillion.minke.data.entity.Transaction
import com.lemillion.minke.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject internal constructor(
    transactionRepository: TransactionRepository
) : ViewModel() {
    val transactions: LiveData<List<Transaction>> =
        transactionRepository.getTransactions().asLiveData()
}