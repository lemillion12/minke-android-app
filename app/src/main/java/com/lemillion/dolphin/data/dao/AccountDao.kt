package com.lemillion.dolphin.data.dao

import androidx.room.*
import com.lemillion.dolphin.data.entity.Account
import com.lemillion.dolphin.data.entity.AccountWithTransactions
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(accounts: List<Account>)

    @Transaction
    @Query("SELECT * FROM Account")
    fun getAccounts(): Flow<List<AccountWithTransactions>>
}