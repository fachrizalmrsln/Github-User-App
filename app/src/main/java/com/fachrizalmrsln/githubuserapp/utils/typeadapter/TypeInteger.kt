package com.fachrizalmrsln.githubuserapp.utils.typeadapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

class TypeInteger : TypeAdapter<Int>() {
    override fun write(out: JsonWriter?, value: Int?) {
        if (value == null) {
            out?.nullValue()
            return
        }
        out?.value(value)
    }

    override fun read(`in`: JsonReader?): Int {
        if (`in`?.peek() == JsonToken.NULL) {
            `in`.nextNull()
            return 0
        }
        val stringValue = `in`?.nextString() as String
        return try {
            stringValue.toInt()
        } catch (e: NumberFormatException) {
            0
        }
    }
}