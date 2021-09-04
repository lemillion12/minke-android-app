package com.lemillion.minke.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lemillion.minke.data.entity.Account
import com.lemillion.minke.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountListViewModel @Inject internal constructor(
    accountRepository: AccountRepository
) : ViewModel() {
    val accounts: LiveData<List<Account>> =
        accountRepository.getAccounts().asLiveData()
}