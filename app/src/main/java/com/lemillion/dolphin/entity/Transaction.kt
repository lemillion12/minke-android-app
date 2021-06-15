package com.lemillion.dolphin.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Transaction(
    @PrimaryKey val transactionId: Int,
    val accountId: Int,
    val transactionDate: Date,
    val direction: TransactionDirection,
    val amount: Double,
    val valueDate: Date?,
    val referenceNumber: String?,
    val description: String?
)

enum class TransactionDirection {
    WITHDRAWAL, DEPOSIT;
}