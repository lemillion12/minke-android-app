package com.lemillion.minke.utility

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun dateToString(date: LocalDate?) = date?.toString()

    @TypeConverter
    fun stringToDate(value: String?) = value?.let { LocalDate.parse(it) }
}