package com.lemillion.dolphin.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lemillion.dolphin.entity.UnenrichedTransaction

@Dao
interface UnenrichedTransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(unenrichedTransactions: List<UnenrichedTransaction>)

    @Query("SELECT * FROM UnenrichedTransaction")
    suspend fun getAll(): List<UnenrichedTransaction>
}