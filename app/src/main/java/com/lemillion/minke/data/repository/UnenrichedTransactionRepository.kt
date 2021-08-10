package com.lemillion.minke.data.repository

import com.lemillion.minke.data.dao.UnenrichedTransactionDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnenrichedTransactionRepository @Inject constructor(
    private val unenrichedTransactionDao: UnenrichedTransactionDao
) {
    fun getUnenrichedTransactions() = unenrichedTransactionDao.getUnenrichedTransactions()
}