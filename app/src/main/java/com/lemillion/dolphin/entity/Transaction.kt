package com.lemillion.dolphin.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Transaction(
    @PrimaryKey @ColumnInfo(name = "rowid") val id: Int,
    val accountId: Int,
    val transactionDate: LocalDate,
    val direction: TransactionDirection,
    val amount: Double,
    val valueDate: LocalDate?,
    val referenceNumber: String?,
    val description: String?
)

enum class TransactionDirection {
    WITHDRAWAL, DEPOSIT;
}