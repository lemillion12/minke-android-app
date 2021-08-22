package com.lemillion.minke.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Transaction(
    val accountId: Int,
    val transactionDate: LocalDate,
    val direction: TransactionDirection,
    val amount: Double,
    val valueDate: LocalDate?,
    val referenceNumber: String?,
    val description: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

enum class TransactionDirection {
    WITHDRAWAL, DEPOSIT;
}