package com.lemillion.dolphin.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class AccountWithTransactions(
    @Embedded val account: Account,
    @Relation(
        parentColumn = "id",
        entityColumn = "accountId"
    )
    val transactions: List<Transaction>
)