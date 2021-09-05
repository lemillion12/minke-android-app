package com.lemillion.minke.data.repository

import com.lemillion.minke.data.dao.TransactionDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao
) {
    fun getTransactions() = transactionDao.getTransactions()
}