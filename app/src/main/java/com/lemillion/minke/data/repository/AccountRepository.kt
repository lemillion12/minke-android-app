package com.lemillion.minke.data.repository

import com.lemillion.minke.data.dao.AccountDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor(
    private val accountDao: AccountDao
) {
    fun getAccounts() = accountDao.getAccounts()
}