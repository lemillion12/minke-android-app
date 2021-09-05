package com.lemillion.minke.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lemillion.minke.data.entity.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(transaction: List<Transaction>)

    @Query("SELECT * FROM `Transaction`")
    fun getTransactions(): Flow<List<Transaction>>
}