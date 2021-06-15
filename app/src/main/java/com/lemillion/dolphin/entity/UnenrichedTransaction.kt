package com.lemillion.dolphin.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class UnenrichedTransaction(
    @PrimaryKey val transactionId: Int,
    val accountNumber: String?,
    val message: String,
    val status: EnrichmentStatus,
    val type: MessageType,
    val updateTime: Date
)

enum class MessageType {
    EXCEL_STATEMENT, TEXT_MESSAGE;
}

enum class EnrichmentStatus {
    PENDING, COMPLETE, FAILED
}
