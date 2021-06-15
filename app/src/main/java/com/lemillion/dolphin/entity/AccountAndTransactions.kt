package com.lemillion.dolphin.entity

import androidx.room.Embedded
import androidx.room.Relation

data class AccountAndTransactions(
    @Embedded val account: Account,
    @Relation(
        parentColumn = "accountId",
        entityColumn = "accountId"
    )
    val transactions: List<Transaction>
)