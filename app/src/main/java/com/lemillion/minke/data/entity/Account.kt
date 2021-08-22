package com.lemillion.minke.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    val type: AccountType,
    val name: String,
    val number: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

enum class AccountType {
    SAVINGS, CREDIT_CARD, WALLET;
}
