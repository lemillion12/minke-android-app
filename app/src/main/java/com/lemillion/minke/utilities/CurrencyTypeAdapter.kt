package com.lemillion.minke.utilities

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.util.*

class CurrencyTypeAdapter : TypeAdapter<Currency>() {
    private val currencyConverter = CurrencyConverter()

    override fun write(output: JsonWriter, value: Currency) {
        output.value(currencyConverter.currencyToString(value))
    }

    override fun read(input: JsonReader): Currency? {
        return currencyConverter.stringToCurrency(input.nextString())
    }
}