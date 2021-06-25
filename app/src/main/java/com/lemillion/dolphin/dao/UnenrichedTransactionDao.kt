package com.lemillion.dolphin.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lemillion.dolphin.entity.UnenrichedTransaction

@Dao
interface UnenrichedTransactionDao {
    @Insert
    fun insertAll(vararg  unenrichedTransactions: List<UnenrichedTransaction>)

    @Query("SELECT * FROM UnenrichedTransaction")
    fun getAll(): List<UnenrichedTransaction>
}