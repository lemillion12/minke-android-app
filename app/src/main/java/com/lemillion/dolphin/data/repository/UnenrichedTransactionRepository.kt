package com.lemillion.dolphin.data.repository

import com.lemillion.dolphin.data.dao.UnenrichedTransactionDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnenrichedTransactionRepository @Inject constructor(
    private val unenrichedTransactionDao: UnenrichedTransactionDao
) {
    fun getUnenrichedTransactions() = unenrichedTransactionDao.getUnenrichedTransactions()
}