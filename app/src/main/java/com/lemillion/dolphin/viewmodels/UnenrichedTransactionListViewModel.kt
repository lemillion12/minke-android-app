package com.lemillion.dolphin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lemillion.dolphin.data.entity.UnenrichedTransaction
import com.lemillion.dolphin.data.repository.UnenrichedTransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UnenrichedTransactionListViewModel @Inject internal constructor(
    unenrichedTransactionRepository: UnenrichedTransactionRepository
) : ViewModel() {
    val unenrichedTransactions: LiveData<List<UnenrichedTransaction>> =
        unenrichedTransactionRepository.getUnenrichedTransactions().asLiveData()
}