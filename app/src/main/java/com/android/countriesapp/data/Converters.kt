package com.android.countriesapp.data

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class Converters {
    private val moshi = Moshi.Builder().build()

    private val stringListType =
        Types.newParameterizedType(List::class.java, String::class.java)

    private val adapter = moshi.adapter<List<String>>(stringListType)

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return adapter.toJson(list)
    }

    @TypeConverter
    fun toStringList(json: String): List<String> {
        return adapter.fromJson(json) ?: emptyList()
    }
}