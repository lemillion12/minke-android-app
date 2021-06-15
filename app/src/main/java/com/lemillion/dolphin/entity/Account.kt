package com.lemillion.dolphin.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey val accountId: Int,
    val type: AccountType,
    val name: String,
    val number: String
)

enum class AccountType {
    SAVINGS, CREDIT_CARD, WALLET;
}