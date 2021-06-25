package com.lemillion.dolphin.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.*

data class UnenrichedTransactionAndTransaction(
    @Embedded val unenrichedTransaction: UnenrichedTransaction,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val transaction: Transaction?
)