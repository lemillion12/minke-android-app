package com.lemillion.dolphin.database

import androidx.room.TypeConverter
import java.time.LocalDate

class DateConverter {
    @TypeConverter
    fun dateToString(date: LocalDate?) = date?.toString()

    @TypeConverter
    fun stringToDate(value: String?) = value?.let { LocalDate.parse(it) }
}