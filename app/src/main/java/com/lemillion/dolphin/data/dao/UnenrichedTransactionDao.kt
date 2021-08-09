package com.lemillion.dolphin.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lemillion.dolphin.data.entity.UnenrichedTransaction
import kotlinx.coroutines.flow.Flow

@Dao
interface UnenrichedTransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(unenrichedTransactions: List<UnenrichedTransaction>)

    @Query("SELECT * FROM UnenrichedTransaction")
    fun getUnenrichedTransactions(): Flow<List<UnenrichedTransaction>>
}