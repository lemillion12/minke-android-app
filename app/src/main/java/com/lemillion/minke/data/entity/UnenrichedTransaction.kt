package com.lemillion.minke.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class UnenrichedTransaction(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val accountNumber: String?,
    val message: String,
    val status: EnrichmentStatus,
    val type: MessageType,
    val insertDate: LocalDate
)

enum class MessageType {
    EXCEL_STATEMENT, TEXT_MESSAGE;
}

enum class EnrichmentStatus {
    PENDING, COMPLETE, FAILED
}
