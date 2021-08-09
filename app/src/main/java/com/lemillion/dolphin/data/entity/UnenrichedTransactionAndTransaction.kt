package com.lemillion.dolphin.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UnenrichedTransactionAndTransaction(
    @Embedded val unenrichedTransaction: UnenrichedTransaction,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val transaction: Transaction?
)