package com.lemillion.dolphin.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.lemillion.dolphin.entity.Account
import com.lemillion.dolphin.entity.AccountWithTransactions

@Dao
interface AccountDao {
    @Insert
    fun insertAll(vararg accounts: Account)

    @Transaction
    @Query("SELECT * FROM Account")
    fun getAll(): List<AccountWithTransactions>
}