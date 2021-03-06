package com.lemillion.minke.utility

import androidx.room.TypeConverter
import java.util.*

class CurrencyConverter {
    @TypeConverter
    fun currencyToString(currency: Currency?) = currency?.currencyCode

    @TypeConverter
    fun stringToCurrency(value: String?) = value?.let { Currency.getInstance(it) }
}