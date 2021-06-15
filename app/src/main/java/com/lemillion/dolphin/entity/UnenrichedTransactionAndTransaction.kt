package com.lemillion.dolphin.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.*

@Entity
data class UnenrichedTransactionAndTransaction(
    @Embedded val unenrichedTransaction: UnenrichedTransaction,
    @Relation(
        parentColumn = "transactionId",
        entityColumn = "transactionId"
    )
    val transaction: Transaction?
)