package com.lemillion.dolphin.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey @ColumnInfo(name = "rowid") val id: Int,
    val type: AccountType,
    val name: String,
    val number: String
)

enum class AccountType {
    SAVINGS, CREDIT_CARD, WALLET;
}
