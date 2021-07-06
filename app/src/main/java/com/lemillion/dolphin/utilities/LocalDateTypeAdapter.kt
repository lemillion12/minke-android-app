package com.lemillion.dolphin.utilities

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDate

class LocalDateTypeAdapter : TypeAdapter<LocalDate>() {
    private val localDateConverter = LocalDateConverter()

    override fun write(output: JsonWriter, value: LocalDate) {
        output.value(localDateConverter.dateToString(value))
    }

    override fun read(input: JsonReader): LocalDate? {
        return localDateConverter.stringToDate(input.nextString())
    }
}