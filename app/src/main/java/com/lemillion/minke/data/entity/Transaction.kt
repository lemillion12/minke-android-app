package com.lemillion.minke.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

@Entity
data class Transaction(
    val accountId: Long,
    val transactionDate: LocalDate,
    val direction: TransactionDirection,
    val amount: Double,
    val currency: Currency,
    val valueDate: LocalDate?,
    val referenceNumber: String?,
    val description: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun toString(): String {
        return "Transaction(accountId=$accountId, transactionDate=$transactionDate, direction=$direction, amount=$amount ${currency.symbol}, valueDate=$valueDate, referenceNumber=$referenceNumber, description=$description, id=$id)"
    }
}

enum class TransactionDirection {
    DEBIT, CREDIT;
}

