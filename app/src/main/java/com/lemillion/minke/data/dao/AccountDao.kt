package com.lemillion.minke.data.dao

import androidx.room.*
import com.lemillion.minke.data.entity.Account
import com.lemillion.minke.data.entity.AccountWithTransactions
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(accounts: List<Account>)

    @Transaction
    @Query("SELECT * FROM Account")
    fun getAccounts(): Flow<List<AccountWithTransactions>>
}